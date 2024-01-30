package com.go.Gra;

import static org.junit.jupiter.api.Assertions.assertSame;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TestObslugiObszaru {

    IObslugaObszaru obliczenia = new ObslugaObszaru();

    @Test
    public void testPionyDoUsunieciaPojedynczePionki(){

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

    }
    
    @Test
    public void testPionyDoUsunieciaPojedynczePionkiGrupaPionkow(){
        IPlansza plansza = new Plansza();
        plansza.dodajPionek("czarny", 2, 1);
        plansza.dodajPionek("czarny", 1, 1);
        plansza.dodajPionek("czarny", 0, 1);
        plansza.dodajPionek("czarny", 3, 1);
        plansza.dodajPionek("czarny", 4, 1);
        plansza.dodajPionek("czarny", 4, 0);

        plansza.dodajPionek("biały", 1, 0);
        plansza.dodajPionek("biały", 2, 0);
        plansza.dodajPionek("biały", 0, 0);

        List <Integer> lista = new ArrayList<>();

        lista.add(3);

        assertSame(3, obliczenia.pionyDoUsuniecia(lista, plansza, "czarny").size());
    }
    @Test
    public void testIloscPionowKoloru(){
        IPlansza plansza = new Plansza();

        assertSame(0, obliczenia.iloscPionkowKoloru(plansza, "czarny"));

        plansza.dodajPionek("czarny", 2, 1);
        plansza.dodajPionek("czarny", 1, 1);
        plansza.dodajPionek("czarny", 0, 1);
        plansza.dodajPionek("czarny", 3, 1);
        plansza.dodajPionek("czarny", 4, 1);
        plansza.dodajPionek("czarny", 4, 0);

        plansza.dodajPionek("biały", 1, 0);
        plansza.dodajPionek("biały", 2, 0);
        plansza.dodajPionek("biały", 0, 0);

        assertSame(6, obliczenia.iloscPionkowKoloru(plansza, "czarny"));
        assertSame(3, obliczenia.iloscPionkowKoloru(plansza, "biały"));
    }
}
