package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Gra implements IGra,IGra2,IGra3,INegocjacjeGra,IWysylanieRuchuDoBazy,Runnable
{
    private String aktywnyKolor;
    private IPlansza plansza;
    private IUsuwaniePionkow usuwaniePionkow;
    private IObslugaObszaru obslugaObszaru;
    private Socket gracz1;
    private Socket gracz2;
    private DataInputStream odbieranieOdGracza1;
    private DataOutputStream wysylanieDoGracza1;
    private DataInputStream odbieranieOdGracza2;
    private DataOutputStream wysylanieDoGracza2;
    private IListenerBazy dodawanie;
    private boolean wczesniejByloPomin; //zmienna która zapamiętuje czy w ostatniej turze pomninięto ruch
    private boolean czyOdczytZBazy;

    public Gra(Socket s1, Socket s2) //konstruktor
    {
        this.aktywnyKolor="czarny";
        this.plansza=new Plansza();
        this.dodawanie = new ListenerBazy();
        this.usuwaniePionkow = new UsuwaniePionkow();
        this.obslugaObszaru = new ObslugaObszaru();
        this.gracz1=s1;
        this.gracz2=s2;
        this.wczesniejByloPomin=false;
        this.czyOdczytZBazy=false;
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

    public void iloscPol()
    {
        if("czarny".equals(aktywnyKolor))
        {
            try
            {
                wysylanieDoGracza1.writeInt(obslugaObszaru.iloscPionkowKoloru(this.plansza,"czarny"));
                wysylanieDoGracza1.writeInt(obslugaObszaru.iloscPionkowKoloru(this.plansza,"biały"));
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
                wysylanieDoGracza2.writeInt(obslugaObszaru.iloscPionkowKoloru(this.plansza,"biały"));
                wysylanieDoGracza2.writeInt(obslugaObszaru.iloscPionkowKoloru(this.plansza,"czarny"));
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
            if ("czarny".equals(aktywnyKolor))
            {
                wysylanieDoGracza1.writeInt(6);
            }
            else
            {
                wysylanieDoGracza2.writeInt(6);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void koniecNegocjacji()
    {
        int status;
        try
        {
            if ("czarny".equals(aktywnyKolor))
            {
                status=odbieranieOdGracza1.readInt();
            }
            else
            {
                status=odbieranieOdGracza2.readInt();
            }
            if (status==-1)
            {
                zerwanoNegocjacje();
            }
            else
            {
                zaakceptowanoTerytoria();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void zerwanoNegocjacje()
    {
        try
        {
            if ("czarny".equals(aktywnyKolor))
            {
                wysylanieDoGracza2.writeInt(2);
            }
            else
            {
                wysylanieDoGracza1.writeInt(2);
            }
            zmienKolor();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void przekazanieTerenow()
    {
        int temp;
        try
        {
            if ("czarny".equals(aktywnyKolor))
            {
                wysylanieDoGracza2.writeInt(100);
                temp=odbieranieOdGracza1.readInt();
                while(temp!=-1)
                {
                    wysylanieDoGracza2.writeInt(temp);
                    temp=odbieranieOdGracza1.readInt();
                }
                wysylanieDoGracza2.writeInt(temp);
                temp=odbieranieOdGracza1.readInt();
                while(temp!=-1)
                {
                    wysylanieDoGracza2.writeInt(temp);
                    temp=odbieranieOdGracza1.readInt();
                }
                wysylanieDoGracza2.writeInt(temp);
            }
            else
            {
                wysylanieDoGracza1.writeInt(100);
                temp=odbieranieOdGracza2.readInt();
                while(temp!=-1)
                {
                    wysylanieDoGracza1.writeInt(temp);
                    temp=odbieranieOdGracza2.readInt();
                }
                wysylanieDoGracza1.writeInt(temp);
                temp=odbieranieOdGracza2.readInt();
                while(temp!=-1)
                {
                    wysylanieDoGracza1.writeInt(temp);
                    temp=odbieranieOdGracza2.readInt();
                }
                wysylanieDoGracza1.writeInt(temp);
            }
            zmienKolor();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void zaakceptowanoTerytoria()
    {
        int temp;
        List<Integer> czarneTerytorium = new ArrayList<Integer>();
        List<Integer> bialeTerytorium = new ArrayList<Integer>();
        try
        {
            if("czarny".equals(aktywnyKolor))
            {
                temp=odbieranieOdGracza1.readInt();
                while(temp!=-1)
                {
                    czarneTerytorium.add(temp);
                    temp=odbieranieOdGracza1.readInt();
                }
                temp=odbieranieOdGracza1.readInt();
                while(temp!=-1)
                {
                    bialeTerytorium.add(temp);
                    temp=odbieranieOdGracza1.readInt();
                }
            }
            else
            {
                temp=odbieranieOdGracza2.readInt();
                while(temp!=-1)
                {
                    bialeTerytorium.add(temp);
                    temp=odbieranieOdGracza2.readInt();
                }
                temp=odbieranieOdGracza2.readInt();
                while(temp!=-1)
                {
                    czarneTerytorium.add(temp);
                    temp=odbieranieOdGracza2.readInt();
                }
            }
            List<Integer> czarnePionyDoUsuniecia = obslugaObszaru.pionyDoUsuniecia(bialeTerytorium, plansza, "biały");
            List<Integer> bialePionyDoUsuniecia = obslugaObszaru.pionyDoUsuniecia(czarneTerytorium, plansza, "czarny");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void ruchDoBazySQL(int pole)
    {
        if(!czyOdczytZBazy)
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
        this.ruchDoBazySQL(nrpola);
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
        ruchDoBazySQL(-1);
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
            zmienKolor();
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
            zmienKolor();
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
            int odp = odbieranieOdGracza1.readInt();
            if(odp==1)
            {
                this.czyOdczytZBazy=true;
            }
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
                    iloscPol();
                }
                if (sygnal==3)
                {
                    koniecNegocjacji();
                }
                if (sygnal==100)
                {
                    przekazanieTerenow();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
