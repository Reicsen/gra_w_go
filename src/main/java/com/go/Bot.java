package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import com.go.GUI.GuiPlansza;
import com.go.GUI.IBot;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import org.apache.commons.math3.random.MersenneTwister;

public class Bot implements Klient, ObslugaPlanszy, IBot, Runnable
{
    private boolean aktywny=false;
    private int iloscJencow=0;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    MersenneTwister generator = new MersenneTwister();

    public Bot(GuiPlansza plansza) //konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            int nrGracza = odbieranieOdSerwera.readInt();
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

    public void losujRuch()
    {
        int x=this.generator.nextInt(19);
        int y=this.generator.nextInt(19);
        wykonajRuch(x, y);
    }

    public void czyPoddacGre()
    {
        //tu będzie obsługa, kiedy bot ma się poddać
    }

    public void ustawKolor(int nrGracza)
    {
        if (nrGracza==1)
        {
            this.kolor=Color.BLACK;
            this.kolorPrzeciwnika=Color.WHITE;
            System.out.println("Ustawiony kolor");
        }
        else
        {
            this.kolor=Color.WHITE;
            this.kolorPrzeciwnika=Color.BLACK;
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
        //do implementacji
    }

    public void usunieciePionka(int nrpola)
    {
        //do implementacji
    }

    public void wypiszKomunikatNaPlanszy(String komunikat)
    {
       //do implementacji
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
                        losujRuch();
                    }
                }
                else
                {
                    if (sygnal==0)
                    {
                        ruch=odbieranieOdSerwera.readInt();   
                        if (ruch==-1)
                        {
                            wypiszKomunikatNaPlanszy("Przegrana!");
                            break;
                        }
                        else
                        {
                            wypiszKomunikatNaPlanszy("Wygrana!");
                            break;
                        }
                    }
                    if (sygnal==2)
                    {
                        zmienAktywnosc();
                        losujRuch();
                    }
                    if (sygnal==1)
                    {
                            ruch=odbieranieOdSerwera.readInt();
                            dodaniePionka(ruch, kolorPrzeciwnika);
                            zmienAktywnosc();
                            wypiszKomunikatNaPlanszy("Twoja tura");
                            losujRuch();
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
                            //plansza.rozpoczecieGry();
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
