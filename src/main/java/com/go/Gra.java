package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Gra implements IGra,IGra2,IGra3,Runnable
{
    private String aktywnyKolor;
    private IPlansza plansza;
    private IUsuwaniePionkow usuwaniePionkow;
    private ITerytorium obliczanieTerytorium;
    private Socket gracz1;
    private Socket gracz2;
    private DataInputStream odbieranieOdGracza1;
    private DataOutputStream wysylanieDoGracza1;
    private DataInputStream odbieranieOdGracza2;
    private DataOutputStream wysylanieDoGracza2;
    private IListenerBazy dodawanie;
    private boolean wczesniejByloPomin; //zmienna która zapamiętuje czy w ostatniej turze pomninięto ruch

    public Gra(Socket s1, Socket s2) //konstruktor
    {
        this.aktywnyKolor="czarny";
        this.plansza=new Plansza();
        this.dodawanie = new ListenerBazy();
        this.usuwaniePionkow = new UsuwaniePionkow();
        this.obliczanieTerytorium = new Terytorium();
        this.gracz1=s1;
        this.gracz2=s2;
        this.wczesniejByloPomin=false;
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

    public void terytorium()
    {
        if("czarny".equals(aktywnyKolor))
        {
            try
            {
                wysylanieDoGracza1.writeInt(obliczanieTerytorium.obliczTerytorium(this.plansza, "czarny"));
                wysylanieDoGracza1.writeInt(obliczanieTerytorium.obliczTerytorium(this.plansza, "biały"));
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
                wysylanieDoGracza2.writeInt(obliczanieTerytorium.obliczTerytorium(this.plansza, "biały"));
                wysylanieDoGracza2.writeInt(obliczanieTerytorium.obliczTerytorium(this.plansza, "czarny"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void pionyUPrzeciwnika()
    {
        if("czarny".equals(aktywnyKolor))
        {
            try
            {
                wysylanieDoGracza1.writeInt(obliczanieTerytorium.iloscPionowNaTerytoriumPrzeciwnika(this.plansza, "czarny"));
                wysylanieDoGracza1.writeInt(obliczanieTerytorium.iloscPionowNaTerytoriumPrzeciwnika(this.plansza, "biały"));
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
                wysylanieDoGracza2.writeInt(obliczanieTerytorium.iloscPionowNaTerytoriumPrzeciwnika(this.plansza, "biały"));
                wysylanieDoGracza2.writeInt(obliczanieTerytorium.iloscPionowNaTerytoriumPrzeciwnika(this.plansza, "czarny"));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void dwaRazyPominietoTure()
    {
        try
        {
            wysylanieDoGracza1.writeInt(6);
            int jency1 = odbieranieOdGracza1.readInt();
            wysylanieDoGracza2.writeInt(6);
            int jency2 = odbieranieOdGracza2.readInt();
            wysylanieDoGracza1.writeInt(101);
            wysylanieDoGracza2.writeInt(101);
            terytorium();
            pionyUPrzeciwnika();
            zmienKolor();
            terytorium();
            pionyUPrzeciwnika();
            zmienKolor();
            wysylanieDoGracza1.writeInt(jency2);
            wysylanieDoGracza2.writeInt(jency1);
            czekajNaOdpowiedz();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void czekajNaOdpowiedz()
    {
        int czarneTerytorium=0;
        int bialeTerytorium=0;
        int temp;
        while (czarneTerytorium==0 || bialeTerytorium==0)
        {
            try
            {
                if ("czarny".equals(aktywnyKolor))
                {
                    temp=odbieranieOdGracza1.readInt();
                    if (temp==3)
                    {
                        temp=odbieranieOdGracza1.readInt();
                        if (temp==1)
                        {
                            czarneTerytorium=odbieranieOdGracza1.readInt();
                            break;
                        }
                        else
                        {
                            wysylanieDoGracza2.writeInt(2);
                        }
                    }
                    else
                    {
                        if(bialeTerytorium!=0)
                        {
                            zmienKolor();
                        }
                    }
                }
                else
                {
                    temp=odbieranieOdGracza2.readInt();
                    if (temp==3)
                    {
                        temp=odbieranieOdGracza2.readInt();
                        if (temp==1)
                        {
                            bialeTerytorium=odbieranieOdGracza2.readInt();
                            break;
                        }
                        else
                        {
                            wysylanieDoGracza1.writeInt(2);
                        }
                    }
                    else
                    {
                        if(czarneTerytorium!=0)
                        {
                            zmienKolor();
                        }
                    }
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        if (czarneTerytorium!=0 && bialeTerytorium!=0)
        {
            if (!"bialy".equals(aktywnyKolor))
            {
                zmienKolor();
            }
            if (czarneTerytorium>bialeTerytorium)
            {
                koniecGry();
            }
            else
            {
                zmienKolor();
                koniecGry();
            }
        }
    }

    private void ruchDoBazy(int pole)
    {
        if ("czarny".equals(this.aktywnyKolor))
        {
            dodawanie.dodaj(1,pole);
        }
        else
        {
            dodawanie.dodaj(2,pole);
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
        this.ruchDoBazy(nrpola);
        this.zmienKolor();
    }

    public void usunPionki(){
        ArrayList <Integer> pionki = usuwaniePionkow.pionkiDoUsuniecia(plansza, aktywnyKolor);
        try{      
            wysylanieDoGracza1.writeInt(4);
            wysylanieDoGracza2.writeInt(4);

            for(int i = 0; i< pionki.size();i++)
            {
                wysylanieDoGracza1.writeInt(pionki.get(i));
                wysylanieDoGracza2.writeInt(pionki.get(i));
            }

            wysylanieDoGracza1.writeInt(-1);
            wysylanieDoGracza2.writeInt(-1);

            usuwaniePionkow.usunPionki(plansza, aktywnyKolor);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void koniecGry()
    {
        ruchDoBazy(-1);
        if ("czarny".equals(aktywnyKolor))
        {
            try
            {
                wysylanieDoGracza1.writeInt(5);
                wysylanieDoGracza1.writeInt(-1);
                wysylanieDoGracza2.writeInt(5);
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
                wysylanieDoGracza2.writeInt(5);
                wysylanieDoGracza2.writeInt(-1);
                wysylanieDoGracza1.writeInt(5);
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
                if(wczesniejByloPomin)
                {
                    dwaRazyPominietoTure();
                    wczesniejByloPomin = false;
                }
                else
                {
                    wysylanieDoGracza2.writeInt(2);
                    wczesniejByloPomin = true;
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
                if(wczesniejByloPomin)
                {
                    dwaRazyPominietoTure();
                    wczesniejByloPomin = false;
                }
                else
                {
                    wysylanieDoGracza1.writeInt(2);
                    wczesniejByloPomin = true;
                }
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

                    int jency = usuwaniePionkow.obliczanieJencow(plansza, aktywnyKolor);
                    wysylanieDoGracza1.writeInt(3);
                    wysylanieDoGracza1.writeInt(jency);

                    usunPionki();
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

                    int jency = usuwaniePionkow.obliczanieJencow(plansza, aktywnyKolor);
                    wysylanieDoGracza2.writeInt(3);
                    wysylanieDoGracza2.writeInt(jency);

                    usunPionki();
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
        wczesniejByloPomin=false;
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
                if (sygnal==11)
                {
                    terytorium();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
