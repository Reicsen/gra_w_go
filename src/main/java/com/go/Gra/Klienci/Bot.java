package com.go.Gra.Klienci;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.commons.math3.random.MersenneTwister;

public class Bot implements Klient, IBot, Runnable
{
    private boolean aktywny=false;
    private int nrGracza;
    private int iloscJencow=0;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    private MersenneTwister generator;

    public Bot() throws BrakSerwera//konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            this.nrGracza = odbieranieOdSerwera.readInt();
            this.generator = new MersenneTwister();
        }
        catch (IOException e)
        {
            throw new BrakSerwera();
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

    public void losujRuch()
    {
        try
        {
            Thread.sleep(1000);
            if (czyPoddacGre())
            {
                poddajSie();
            }
            else
            {
                int x=this.generator.nextInt(19);
                int y=this.generator.nextInt(19);
                wykonajRuch(x, y);
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public boolean czyPoddacGre()
    {
        try
        {
            wysylanieDoSerwera.writeInt(11);
            int iloscPionkow = odbieranieOdSerwera.readInt(); 
            int iloscPionkowPrzeciwnika = odbieranieOdSerwera.readInt();
            int wspolczynnik = iloscPionkowPrzeciwnika-iloscPionkow-this.iloscJencow-10;
            if (wspolczynnik>0)
            {
                int los = this.generator.nextInt(100);
                if (wspolczynnik>los)
                {
                    return true;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public void jencyISygnal4()
    {
        int sygnal;
        try
        {
            sygnal=odbieranieOdSerwera.readInt();
            if (sygnal==3)
            {
                sygnal=odbieranieOdSerwera.readInt();
                this.iloscJencow=this.iloscJencow+sygnal;
                sygnal=odbieranieOdSerwera.readInt();
            }
            if (sygnal==4)
            {
                sygnal=odbieranieOdSerwera.readInt();
                while (sygnal!=-1)
                {
                    sygnal=odbieranieOdSerwera.readInt();
                }
            } 
        }
        catch(IOException e)
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
                    jencyISygnal4();
                    zmienAktywnosc();
                }
                if (sygnal==-1)
                {
                    losujRuch();
                }

                if (sygnal==1)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    zmienAktywnosc();
                    jencyISygnal4();
                    losujRuch();
                }
                if (sygnal==2)
                {
                    zmienAktywnosc();
                    losujRuch();
                }
                               
                if (sygnal==5)
                {
                    break;
                }
                
                if(sygnal==6 || sygnal==8 || sygnal==9 || sygnal==100)
                {
                    poddajSie();
                }
                if(sygnal==7)
                {
                    wysylanieDoSerwera.writeInt(iloscJencow);
                    poddajSie();
                }

                if (sygnal==10)
                {
                    if(this.nrGracza==1)
                    {
                        wysylanieDoSerwera.writeInt(0); //to nie odczyt z bazy
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
