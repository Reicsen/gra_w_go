package com.go.Poprawnosc;

import com.go.IPlansza;

public class NachodzacePionki implements IPoprawnosc{
    public boolean sprawdzPoprawnosc(IPlansza plansza, int x, int y, String kolor){

        if (plansza.podajPola().get(19*y+x).podajPionek()!=null)
        {
            return false;
        }
        IPoprawnosc p = new PoprawnaLiczbaOddechow();
        return p.sprawdzPoprawnosc(plansza, x, y, kolor);
    }
}
