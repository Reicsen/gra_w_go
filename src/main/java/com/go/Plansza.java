package com.go;

import java.util.ArrayList;
import java.util.List;

public class Plansza implements IPlansza
{
    protected List<IPole> pola;

    public Plansza()
    {
        this.pola = new ArrayList<IPole>();
        for (int i=0;i<361;i++)
        {
            IPole temp = new Pole();
            pola.add(temp);
        }
    }

    public void dodajPionek(String kolor, int x, int y)
    {
        IPole temp = pola.get(19*y+x);
        temp.dodajPionek(kolor);
    }

    public boolean sprawdzPoprawnosc(String kolor, int x, int y)
    {
        if (pola.get(19*y+x).podajPionek()!=null)
        {
            return false;
        }
        return true;
    }
}
