package com.go.Gra;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TestUsuwaniePionkow {
    @Test
    public void testObliczanieJencow(){
        IUsuwaniePionkow obliczenia = new UsuwaniePionkow();
        IPlansza plansza = new Plansza();
        
        plansza.dodajPionek("Biały", 0, 0);
        plansza.dodajPionek("Biały", 1, 0);

        plansza.dodajPionek("Czarny", 0, 1);
        plansza.dodajPionek("Czarny", 1, 1);
        plansza.dodajPionek("Czarny", 2, 1);
        plansza.dodajPionek("Czarny", 2, 0);
    
        assertSame(2, obliczenia.obliczanieJencow(plansza, "Biały"));
    }
    @Test
    public void testPionkiDoUsuniecia(){
        IUsuwaniePionkow obliczenia = new UsuwaniePionkow();
        IPlansza plansza = new Plansza();

        plansza.dodajPionek("Biały", 0, 0);
        plansza.dodajPionek("Biały", 1, 0);

        plansza.dodajPionek("Czarny", 0, 1);
        plansza.dodajPionek("Czarny", 1, 1);
        plansza.dodajPionek("Czarny", 2, 1);
        plansza.dodajPionek("Czarny", 2, 0);

        ArrayList<Integer> pionki = obliczenia.pionkiDoUsuniecia(plansza, "Biały");
        assertSame(2, pionki.size());
        assertSame(0, pionki.get(0));
        assertSame(1, pionki.get(1));

        assertSame(5, plansza.podajPola().get(2).podajKamien().podajOddechy());
        assertTrue(!plansza.sprawdzPoprawnosc("Czarny", 0, 0));
    }
    @Test
    public void testUsunPionki(){
        IUsuwaniePionkow obliczenia = new UsuwaniePionkow();
        IPlansza plansza = new Plansza();

        plansza.dodajPionek("Biały", 0, 0);
        plansza.dodajPionek("Biały", 1, 0);

        plansza.dodajPionek("Czarny", 0, 1);
        plansza.dodajPionek("Czarny", 1, 1);
        plansza.dodajPionek("Czarny", 2, 1);
        plansza.dodajPionek("Czarny", 2, 0);

        obliczenia.usunPionki(plansza, "Biały");

        assertSame(8, plansza.podajPola().get(2).podajKamien().podajOddechy());
        assertTrue(plansza.sprawdzPoprawnosc("Czarny", 0, 0));
    }
}
