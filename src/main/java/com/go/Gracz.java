package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import com.go.GUI.GuiPlansza;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class Gracz implements Klient, ObslugaPlanszy, Runnable
{
    private boolean aktywny=false;
    private int iloscJencow=0;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    private GuiPlansza plansza;

    public Gracz(GuiPlansza plansza) //konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            int nrGracza = odbieranieOdSerwera.readInt();
            this.plansza=plansza;
            ustawKolor(nrGracza);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Thread watek = new Thread(this); //stworzenie wątku
        watek.start();
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

    public void pominRuch()
    {
        if (aktywny)
        {
            try
            {
                zmienAktywnosc();
                wysylanieDoSerwera.writeInt(0);
                wypiszKomunikatNaPlanszy("Tura przeciwnika");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
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

    public void ustawKolor(int nrGracza)
    {
        if (nrGracza==1)
        {
            this.kolor=Color.BLACK;
            this.kolorPrzeciwnika=Color.WHITE;
            plansza.kolor=Color.BLACK;
            System.out.println("Ustawiony kolor");
        }
        else
        {
            this.kolor=Color.WHITE;
            this.kolorPrzeciwnika=Color.BLACK;
            plansza.kolor=Color.WHITE;
            System.out.println("Ustawiony kolor");
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


    public void dodaniePionka(int nrpola, Color kolor)
    {
        Platform.runLater(() -> {
        plansza.pionki.get(nrpola).zmienPrzyciskNaKolo( plansza.pionki.get(nrpola), kolor);
        });
    }

    public void usunieciePionka(int nrpola)
    {
    
    }

    public void wypiszKomunikatNaPlanszy(String komunikat)
    {
        Platform.runLater(() -> {
            plansza.lbl.setText(komunikat);
        });
    }

    @Override
    public void run() //metoda obsługująca działanie wątkowe - odbieranie sygnałów z serwera i uruchamianie odpowiednich metod z interfejsów
    {
        System.out.println("in run");
        int sygnal;
        int ruch;

        while (true)
        {
            try
            {
                sygnal=odbieranieOdSerwera.readInt();
                
                    if (aktywny)
                    {
                        if (sygnal==0)
                        {
                            ruch=odbieranieOdSerwera.readInt();
                            dodaniePionka(ruch, kolor);
                            zmienAktywnosc();
                            wypiszKomunikatNaPlanszy("Tura przeciwnika");
                        }
                        else
                        {
                            wypiszKomunikatNaPlanszy("Niepoprawny ruch, spróbuj ponownie");
                        }
                    }
                    else
                    {
                        if (sygnal==0)
                        {
                            ruch=odbieranieOdSerwera.readInt();
                            
                            if (ruch==-1)
                            {
                                wypiszKomunikatNaPlanszy("Przegrałeś!");
                                break;
                            }
                            else
                            {
                                wypiszKomunikatNaPlanszy("Wygrałeś!");
                                break;
                            }
                        }
                        if (sygnal==2)
                        {
                            zmienAktywnosc();
                            wypiszKomunikatNaPlanszy("Twoja tura");
                        }
                        if (sygnal==1)
                        {
                            ruch=odbieranieOdSerwera.readInt();
                            dodaniePionka(ruch, kolorPrzeciwnika);
                            zmienAktywnosc();
                            wypiszKomunikatNaPlanszy("Twoja tura");
                        }
                        if (sygnal==-1)
                        {
                            ruch=odbieranieOdSerwera.readInt();
                            while (ruch!=-1)
                            {
                                usunieciePionka(ruch);
                                ruch=odbieranieOdSerwera.readInt();
                            }
                        }
                        if(sygnal==10)
                        {

                            plansza.rozpoczecieGry();
                            wypiszKomunikatNaPlanszy("Tura przeciwnika");
                            System.out.println("sygnal 10");
                        }
                        //na razie obsługa jeńców nie jest implementowana, bo i tak mechanika ich nie obejmuje
                    }
                
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    

    }
}
