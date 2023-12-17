package com.go;

import java.util.ArrayList;
import java.util.List;

public class Plansza
{
    protected List<Pole> pola;

    public Plansza()
    {
        this.pola = new ArrayList<Pole>();
        for (int i=0;i<361;i++)
        {
            Pole temp = new Pole();
            pola.add(temp);
        }
    }

    protected void dodajPionek(String kolor, int x, int y)
    {
        Pole temp = new Pole(kolor);
        pola.set(19*y+x, temp);
    }

    protected boolean sprawdzPoprawnosc(String kolor, int x, int y)
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
