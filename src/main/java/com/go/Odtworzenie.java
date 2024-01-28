package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import com.go.GUI.GuiPlanszaOdtworzenia;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class Odtworzenie implements Klient, ObslugaPlanszy, IOdtwarzanie, Runnable
{
    private List<Integer> ruchy;
    private List<Integer> gracze;
    private boolean aktywny=false;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    private GuiPlanszaOdtworzenia plansza;
    private int indeks;
    private int nrGracza;

    public Odtworzenie(GuiPlanszaOdtworzenia plansza, List<Integer> listaRuchow, List<Integer> listaGraczy) //konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            this.nrGracza = odbieranieOdSerwera.readInt();
            this.plansza=plansza;
            this.ruchy=listaRuchow;
            this.gracze=listaGraczy;
            ustawKolor(nrGracza);
            indeks=0;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Thread watek = new Thread(this); //stworzenie wątku
        watek.start();
    }

    public void wykonajRuch(int x, int y)
    {
        if (aktywny)
        {
            try
            {
                wysylanieDoSerwera.writeInt(1);
                wysylanieDoSerwera.writeInt(x+19*y);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    public void pominRuch()
    {
        if (aktywny)
        {
            try
            {
                zmienAktywnosc();
                wysylanieDoSerwera.writeInt(0);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void poddajSie()
    {
        if (aktywny)
        {
            try
            {
                zmienAktywnosc();
                wysylanieDoSerwera.writeInt(-1);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void zmienAktywnosc()
    {
        if (aktywny)
        {
            this.aktywny=false;
        }
        else
        {
            this.aktywny=true;
        }
    }

    public void ustawKolor(int nrgracza)
    {
        if (nrgracza==1)
        {
            this.kolor=Color.BLACK;
            this.kolorPrzeciwnika=Color.WHITE;
            plansza.kolor=Color.BLACK;
        }
        else
        {
            this.kolor=Color.WHITE;
            this.kolorPrzeciwnika=Color.BLACK;
            plansza.kolor=Color.WHITE;
        }
    }

    public void dodaniePionka(int nrpola, Color kolor)
    {
        Platform.runLater(() -> {
        plansza.pionki.get(nrpola).zmienPrzyciskNaKolo( plansza.pionki.get(nrpola), kolor);
        });
    }

    public void usunieciePionka(int nrpola)
    {
        Platform.runLater(() -> {
            plansza.pionki.get(nrpola).zmienPrzyciskNaKrzyzyk( plansza.pionki.get(nrpola));
        });
    }

    public void wypiszKomunikatNaPlanszy(String komunikat){}

    public void okienko(String komunikat){
        Platform.runLater(() -> {
            plansza.wyskakujaceOkienko(komunikat);
        });
    }

    public synchronized void odtworzRuch()
    {
        try
        {
            Thread.sleep(1000);
            if(gracze.get(indeks)==this.nrGracza)
            {
                int nastepnyRuch=ruchy.get(indeks);
                if (nastepnyRuch==-1)
                {
                    poddajSie();
                }
                else
                {
                    wykonajRuch(nastepnyRuch%19, nastepnyRuch/19);
                }
            }
            else
            {
                pominRuch();
            }
            indeks=indeks+1;
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run() //metoda obsługująca działanie wątkowe - odbieranie sygnałów z serwera i uruchamianie odpowiednich metod z interfejsów
    {
        int sygnal;
        int ruch;

        while (true)
        {
            try
            {
                sygnal=odbieranieOdSerwera.readInt();
                if (sygnal==0)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    dodaniePionka(ruch, kolor);
                    zmienAktywnosc();
                }
                if (sygnal==-1)
                {
                    odtworzRuch(); 
                }

                if (sygnal==1)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    dodaniePionka(ruch, kolorPrzeciwnika);
                    zmienAktywnosc();
                    indeks=indeks+1;
                    odtworzRuch();                    
                }
                if (sygnal==2)
                {
                    zmienAktywnosc();
                    odtworzRuch();
                }

                if (sygnal==4)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    while (ruch!=-1)
                    {
                        usunieciePionka(ruch);
                        ruch=odbieranieOdSerwera.readInt();
                    }
                }  
                        
                if (sygnal==5)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    if (nrGracza==1)
                    {                  
                        if (ruch==-1)
                        {
                            okienko("Wygrał biały");
                        }
                        else
                        {
                            okienko("Wygrał czarny");
                        }
                    }
                    break;
                }
                
                if(sygnal==3)
                {
                    ruch=odbieranieOdSerwera.readInt();
                }
                if(sygnal==10)
                {
                    if (this.nrGracza==1)
                    {
                        wysylanieDoSerwera.writeInt(1); //to jest odczyt z bazy
                    }
                }
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
