package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.scene.paint.Color;

public class Gracz implements Klient, ObslugaPlanszy, Runnable
{
    private boolean aktywny=false;
    private int iloscJencow=0;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    Socket polaczenieZSerwerem;

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
        }
        else
        {
            this.kolor=Color.WHITE;
            this.kolorPrzeciwnika=Color.BLACK;
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

    private void polacz()
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

        Thread watek = new Thread(this);
        watek.start();
    }

    private void wlaczGUI()
    {
        //miejsce na GUI? (a przynajmniej tak było w przykładzie)

        polacz();
    }

    public void dodaniePionka(int nrpola, Color kolor)
    {
        //dodanie do wizualnej planszy na wskazanym polu pionka o danym kolorze
    }

    public void usunieciePionka(int nrpola)
    {
        //usunięcie z wizualnej planszy na wskazanym polu pionka (zamiana go w ,,krzyżyk"); na razie puste
    }

    public void wypiszKomunikatNaPlanszy(String komunikat)
    {
        //wyświetlenie w okienku GUI komunikatu
    }

    public static void main(String[] args)
    {
        Gracz gracz = new Gracz();
        gracz.wlaczGUI();
    }

    @Override
    public void run()
    {
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
                        wypiszKomunikatNaPlanszy("Tura przeciwnika");
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
