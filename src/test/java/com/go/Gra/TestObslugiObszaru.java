package com.go.Gra;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestObslugiObszaru {

    IObslugaObszaru obliczenia = new ObslugaObszaru();

    @Test
    public void testPionyDoUsuniecia(){

        IPlansza plansza = new Plansza();
        plansza.dodajPionek("biały", 0, 0);
        plansza.dodajPionek("czarny", 2, 0);
        plansza.dodajPionek("czarny", 2, 1);
        plansza.dodajPionek("czarny", 1, 1);
        plansza.dodajPionek("czarny", 0, 1);

        List <Integer> lista = new ArrayList<>();
        lista.add(1);

        assertSame(1, obliczenia.pionyDoUsuniecia(lista, plansza, "czarny").size());
        assertSame(0, obliczenia.pionyDoUsuniecia(lista, plansza, "czarny").get(0));

        plansza.usunPionek(2, 0);
        plansza.dodajPionek("czarny", 3, 1);
        plansza.dodajPionek("czarny", 4, 1);
        plansza.dodajPionek("czarny", 4, 0);

        plansza.dodajPionek("biały", 1, 0);
        plansza.dodajPionek("biały", 2, 0);

        lista.set(0, 3);

        assertSame(3, obliczenia.pionyDoUsuniecia(lista, plansza, "czarny").size());
    }
    
}
