package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.commons.math3.random.MersenneTwister;

public class Bot implements Klient, IBot, INegocjacje, Runnable
{
    private boolean aktywny=false;
    private int nrGracza;
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
            this.nrGracza = odbieranieOdSerwera.readInt();
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
            System.out.println("Prośba o terytorium");
            int wlasneTerytorium = odbieranieOdSerwera.readInt(); 
            int wrogieTerytorium = odbieranieOdSerwera.readInt();
            System.out.println("Otrzymano: "+wlasneTerytorium+" "+wrogieTerytorium);
            if (wrogieTerytorium>wlasneTerytorium+10)
            {
                int los = this.generator.nextInt(100);
                System.out.println("Los: "+los);
                if (wrogieTerytorium-wlasneTerytorium-10>los)
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

    public void dane()
    {
        try
        {
            int wlasneTerytorium = odbieranieOdSerwera.readInt();
            int przeciwneTerytorium = odbieranieOdSerwera.readInt();
            int przeciwnePionkiNaWlasnymTerytorium = odbieranieOdSerwera.readInt();
            int wlasnePionkiNaPrzeciwnymTerytorium = odbieranieOdSerwera.readInt();  
            int jencyRywala = odbieranieOdSerwera.readInt();          

            if(this.generator.nextInt(2)==0)
            {
                wybranoNie();
            }
            else
            {
                wybranoTak(wlasneTerytorium, jencyRywala, wlasnePionkiNaPrzeciwnymTerytorium);
            }                
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void wybranoTak(int twojeTerytorium, int jencyPrzeciwnika, int twojePionkiNaTereniePrzeciwnika)
    {
        try
        {
            wysylanieDoSerwera.writeInt(3);
            wysylanieDoSerwera.writeInt(1);
            wysylanieDoSerwera.writeInt(Math.max(0, twojeTerytorium-(jencyPrzeciwnika+twojePionkiNaTereniePrzeciwnika)));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void wybranoNie()
    {
        try
        {
            wysylanieDoSerwera.writeInt(3);
            wysylanieDoSerwera.writeInt(-1);
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
                System.out.println("Otrzymano: "+sygnal);
                if (sygnal==0)
                {
                    ruch=odbieranieOdSerwera.readInt();
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

                if (sygnal==4)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    while (ruch!=-1)
                    {
                        ruch=odbieranieOdSerwera.readInt();
                    }
                } 
                
                if(sygnal==6)
                {
                    wysylanieDoSerwera.writeInt(iloscJencow);
                }
                if (sygnal==101)
                {
                    dane();
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
