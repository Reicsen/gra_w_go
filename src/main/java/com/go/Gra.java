package com.go;

import java.net.Socket;

public class Gra implements Runnable
{
    private boolean koniecGry;
    private String aktywnyKolor;
    private IPlansza plansza;
    private Socket gracz1;
    private Socket gracz2;

    public Gra(Socket s1, Socket s2)
    {
        this.aktywnyKolor="czarny";
        this.plansza=new Plansza();
        this.koniecGry=false;
        this.gracz1=s1;
        this.gracz2=s2;
    }

    private void zmienKolor()
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

    private boolean sprawdzPoprawnosc(int nrpola)
    {
        return plansza.sprawdzPoprawnosc(this.aktywnyKolor, nrpola%19, nrpola/19);
    }

    private void dodajPionek(int nrpola)
    {
        if (sprawdzPoprawnosc(nrpola))
        {
            plansza.dodajPionek(this.aktywnyKolor, nrpola%19, nrpola/19);
            this.zmienKolor();
        }
    }
}