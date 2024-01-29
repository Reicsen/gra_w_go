package com.go.Gra;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestIlosciPionowWKolorze
{
    IObslugaObszaru obliczenia = new ObslugaObszaru();

    @Test
    public void testIlosciPionowWKolorze()
    {

        IPlansza plansza = new Plansza();
        
        for (int x=0; x<19; x++)
        {
            for (int y=0; y<3; y++)
            {
                plansza.dodajPionek("czarny", x, y);
                if ((x+y)%2==0)
                {
                    plansza.dodajPionek("biały", x, 18-y);
                }
            }
        }

        assertEquals(0,obliczenia.iloscPionkowKoloru(plansza,"czerwony"));
        assertEquals(57,obliczenia.iloscPionkowKoloru(plansza,"czarny"));
        assertEquals(29,obliczenia.iloscPionkowKoloru(plansza,"biały"));
    }
}
