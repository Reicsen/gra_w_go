package com.go;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TestTerytorium {

    ITerytorium obliczenia = new Terytorium();

    @Test
    public void testObliczTerytorium(){
        IPlansza plansza = new Plansza();
        plansza.dodajPionek("biały", 0, 0);
        plansza.dodajPionek("czarny", 2, 0);
        plansza.dodajPionek("czarny", 2, 1);
        plansza.dodajPionek("czarny", 1, 1);
        plansza.dodajPionek("czarny", 0, 1);

        assertSame(0, obliczenia.obliczTerytorium(plansza, "biały"));

    }
    @Test
    public void testPionyNaTerytoriumPrzeciwnika(){
        IPlansza plansza = new Plansza();
        plansza.dodajPionek("biały", 0, 0);
        plansza.dodajPionek("czarny", 2, 0);
        plansza.dodajPionek("czarny", 2, 1);
        plansza.dodajPionek("czarny", 1, 1);
        plansza.dodajPionek("czarny", 0, 1);

        assertSame(0, obliczenia.pionyNaTerytoriumPrzeciwnika(plansza, "bialy").size());
    }
    
}
