package com.go;

public class Serwer
{
    private int iloscGraczy;
    private String aktywnyKolor;
    private IPlansza plansza;

    public Serwer()
    {
        this.iloscGraczy=0;
        this.aktywnyKolor="czarny";
        this.plansza=new Plansza();
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

    private boolean sprawdzPoprawnosc(int x, int y)
    {
        return plansza.sprawdzPoprawnosc(this.aktywnyKolor, x, y);
    }

    private void dodajPionek(int x, int y)
    {
        if (sprawdzPoprawnosc(x, y))
        {
            plansza.dodajPionek(this.aktywnyKolor, x, y);
            this.zmienKolor();
        }
    }
}
