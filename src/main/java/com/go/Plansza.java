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
        if (x<0 || x >18 || y<0 || y>18)
        {
            return false;
        }
        if (pola.get(19*y+x)!=null)
        {
            return false;
        }
        return true;
    }
}
