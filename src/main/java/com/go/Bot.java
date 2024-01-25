package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import com.go.GUI.IBot;
import org.apache.commons.math3.random.MersenneTwister;

public class Bot implements Klient, IBot, Runnable
{
    private boolean aktywny=false;
    private int iloscJencow=0;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    MersenneTwister generator = new MersenneTwister();

    public Bot() //konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
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
        try
        {
            Thread.sleep(1);
            czyPoddacGre();
            if (aktywny)
            {
                int n=this.generator.nextInt(10);
                if(n==0)
                {
                    pominRuch();
                }
                else
                {
                    int x=this.generator.nextInt(19);
                    int y=this.generator.nextInt(19);
                    wykonajRuch(x, y);
                }
            }
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void czyPoddacGre()
    {
        try
        {
            wysylanieDoSerwera.writeInt(11);
            int wlasneTerytorium = odbieranieOdSerwera.readInt(); 
            wysylanieDoSerwera.writeInt(12);
            int wrogieTerytorium = odbieranieOdSerwera.readInt();
            if (wrogieTerytorium>wlasneTerytorium+10)
            {
                int los = this.generator.nextInt(100);
                if (wrogieTerytorium-wlasneTerytorium>los)
                {
                    poddajSie();
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
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
                    zmienAktywnosc();
                }
                if (sygnal==-1)
                {
                    losujRuch();
                }

                if (sygnal==1)
                {
                    zmienAktywnosc();
                    losujRuch();
                }
                if (sygnal==2)
                {
                    zmienAktywnosc();
                    losujRuch();
                }

                if(sygnal==3)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    this.iloscJencow=this.iloscJencow+ruch;
                }
                               
                if (sygnal==5)
                {
                    break;
                }
                if(sygnal==100) //żeby otrzymanie następnego sygnału (wartości terytorium) czegoś nie aktywowało
                {
                    ruch=odbieranieOdSerwera.readInt();
                }                     
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }    
    }
}
