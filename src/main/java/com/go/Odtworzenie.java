package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.go.GUI.GuiPlanszaOdtworzenia;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class Odtworzenie implements Klient, ObslugaPlanszy, IOdtwarzanie, Runnable
{
    private List<Integer> ruchy;
    private boolean aktywny=false;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    private GuiPlanszaOdtworzenia plansza;

    public Odtworzenie(GuiPlanszaOdtworzenia plansza, int nrGry) //konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            int nrGracza = odbieranieOdSerwera.readInt();
            this.plansza=plansza;
            this.ruchy = new ArrayList<Integer>();
            ustawKolor(nrGracza);
            sczytajRuchy(nrGry,nrGracza);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Thread watek = new Thread(this); //stworzenie wątku
        watek.start();
    }

    public void sczytajRuchy(int nrGry, int nrGracza)
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            String temp="EXECUTE lista_ruchow USING "+nrGry+", "+nrGracza+";";
            ResultSet wynik = kwerenda.executeQuery(temp);
            while(wynik.next())
            {
                int n = wynik.getInt(1);
                this.ruchy.add(n);
            }
            wynik.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
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
        try
        {
            Thread.sleep(1);
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
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void odtworzRuch(int nrPola)
    {
        if(nrPola==-1)
        {
            poddajSie();
        }
        else
        {
            int x=nrPola%19;
            int y=nrPola/19;
            wykonajRuch(x, y);
        }
    }

    public void ustawKolor(int nrgracza)
    {
        if (nrgracza==1)
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
        Platform.runLater(() -> {
            plansza.pionki.get(nrpola).zmienPrzyciskNaKrzyzyk( plansza.pionki.get(nrpola));
        });
    }

    public void wypiszKomunikatNaPlanszy(String komunikat)
    {
        Platform.runLater(() -> {
            plansza.lbl.setText(komunikat);
        });
    }

    public void okienko(String komunikat){
        Platform.runLater(() -> {
            plansza.wyskakujaceOkienko(komunikat);
        });
    }

    @Override
    public void run() //metoda obsługująca działanie wątkowe - odbieranie sygnałów z serwera i uruchamianie odpowiednich metod z interfejsów
    {
        int sygnal;
        int ruch;
        int licznik=0;

        while (true)
        {
            int pole=this.ruchy.get(licznik);
            try
            {
                sygnal=odbieranieOdSerwera.readInt();
                if (sygnal==0)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    dodaniePionka(ruch, kolor);
                    zmienAktywnosc();
                    licznik=licznik+1;
                }
                if (sygnal==-1)
                {
                    odtworzRuch(pole); 
                }

                if (sygnal==1)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    dodaniePionka(ruch, kolorPrzeciwnika);
                    zmienAktywnosc();
                    odtworzRuch(pole);                    
                }
                if (sygnal==2)
                {
                    zmienAktywnosc();
                    odtworzRuch(pole);
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
                    if (ruch==-1)
                    {
                        okienko("Przegrałeś :C");
                        break;
                    }
                    else
                    {
                        okienko("Wygrałeś!");
                        break;
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
