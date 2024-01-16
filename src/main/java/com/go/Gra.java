package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Gra implements IGra,IGra2,Runnable
{
    private String aktywnyKolor;
    private IPlansza plansza;
    private IUsuwaniePionkow usuwaniePionkow;
    private Socket gracz1;
    private Socket gracz2;
    private DataInputStream odbieranieOdGracza1;
    private DataOutputStream wysylanieDoGracza1;
    private DataInputStream odbieranieOdGracza2;
    private DataOutputStream wysylanieDoGracza2;

    public Gra(Socket s1, Socket s2) //konstruktor
    {
        this.aktywnyKolor="czarny";
        this.plansza=new Plansza();
        this.usuwaniePionkow = new UsuwaniePionkow();
        this.gracz1=s1;
        this.gracz2=s2;
        try
        {
            this.odbieranieOdGracza1 = new DataInputStream(gracz1.getInputStream());
            this.wysylanieDoGracza1 = new DataOutputStream(gracz1.getOutputStream());
            this.odbieranieOdGracza2 = new DataInputStream(gracz2.getInputStream());
            this.wysylanieDoGracza2 = new DataOutputStream(gracz2.getOutputStream());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    private void zmienKolor() //metoda zmieniająca aktywny kolor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        if ("czarny".equals(this.aktywnyKolor))
        {
            aktywnyKolor="biały";
        }
        else
        {
            aktywnyKolor="czarny";
        }
    }

    public boolean sprawdzPoprawnosc(int nrpola)
    {
        return plansza.sprawdzPoprawnosc(this.aktywnyKolor, nrpola%19, nrpola/19);
    }

    public void dodajPionek(int nrpola)
    {
        plansza.dodajPionek(this.aktywnyKolor, nrpola%19, nrpola/19);
        this.zmienKolor();
    }

    public void koniecGry()
    {
        if ("czarny".equals(aktywnyKolor))
        {
            try
            {
                wysylanieDoGracza1.writeInt(0);
                wysylanieDoGracza1.writeInt(-1);
                wysylanieDoGracza2.writeInt(0);
                wysylanieDoGracza2.writeInt(1);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                wysylanieDoGracza2.writeInt(0);
                wysylanieDoGracza2.writeInt(-1);
                wysylanieDoGracza1.writeInt(0);
                wysylanieDoGracza1.writeInt(1);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void pominiecieTury()
    {
        if ("czarny".equals(aktywnyKolor))
        {
            try
            {
                wysylanieDoGracza2.writeInt(2);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                wysylanieDoGracza1.writeInt(2);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        zmienKolor();
    }

    public void probaRuchu()
    {
        int ruch;
        if ("czarny".equals(aktywnyKolor))
        {
            try
            {
                ruch=odbieranieOdGracza1.readInt();
                if (sprawdzPoprawnosc(ruch))
                {
                    wysylanieDoGracza1.writeInt(0);
                    wysylanieDoGracza1.writeInt(ruch);
                    wysylanieDoGracza2.writeInt(1);
                    wysylanieDoGracza2.writeInt(ruch);
                    dodajPionek(ruch);

                    int jency = usuwaniePionkow.obliczanieJencow(plansza);
                    wysylanieDoGracza1.writeInt(3);
                    wysylanieDoGracza1.writeInt(jency);

                    wysylanieDoGracza1.writeInt(5);
                    wysylanieDoGracza1.writeInt(miejsce);
                    wysylanieDoGracza1.writeInt(miejsce);
                    wysylanieDoGracza1.writeInt(-1);

                    wysylanieDoGracza2.writeInt(5);
                    wysylanieDoGracza2.writeInt(miejsce);
                    wysylanieDoGracza2.writeInt(miejsce);
                    wysylanieDoGracza2.writeInt(-1);

                    

                    //TODO liczenie jeńców i usuwanie pionków
                }
                else
                {
                    wysylanieDoGracza1.writeInt(-1);
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                ruch=odbieranieOdGracza2.readInt();
                if (sprawdzPoprawnosc(ruch))
                {
                    wysylanieDoGracza2.writeInt(0);
                    wysylanieDoGracza2.writeInt(ruch);
                    wysylanieDoGracza1.writeInt(1);
                    wysylanieDoGracza1.writeInt(ruch);
                    dodajPionek(ruch);
                    //TODO liczenie jeńców i usuwanie pionków

                    int jency = usuwaniePionkow.obliczanieJencow(plansza);
                    wysylanieDoGracza1.writeInt(3);
                    wysylanieDoGracza1.writeInt(jency);
                }
                else
                {
                    wysylanieDoGracza2.writeInt(-1);
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() //metoda obsługująca działanie wątkowe poprzez wywoływanie odpowiednich metod z interfejsów
    {
        try
        {
            wysylanieDoGracza1.writeInt(10);
            wysylanieDoGracza2.writeInt(10);
            wysylanieDoGracza1.writeInt(2); //poinformowanie graczy o starcie gry, a gracza 1. o początku jego tury
            int sygnal;

            while (true)
            {
                if ("czarny".equals(aktywnyKolor))
                {
                    sygnal=odbieranieOdGracza1.readInt();
                }
                else
                {
                    sygnal=odbieranieOdGracza2.readInt();
                }
                if (sygnal==-1)
                {
                    koniecGry();
                    break;
                }
                if (sygnal==0)
                {
                    pominiecieTury();
                }
                if (sygnal==1)
                {
                    probaRuchu();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
