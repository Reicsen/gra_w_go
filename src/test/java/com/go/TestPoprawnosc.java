package com.go;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.go.Poprawnosc.IPoprawnosc;
import com.go.Poprawnosc.Ko;
import com.go.Poprawnosc.NachodzacePionki;
import com.go.Poprawnosc.PoprawnaLiczbaOddechow;

public class TestPoprawnosc {
    
    @Test
    public void testNachodzacePionki(){
        IPoprawnosc warunek = new NachodzacePionki();
        Plansza p = new Plansza();

        assertTrue(warunek.sprawdzPoprawnosc(p, 0, 0, "Bialy"));

        p.dodajPionek("Bialy", 0, 0);

        assertTrue( !(warunek.sprawdzPoprawnosc(p, 0, 0, "Bialy")) );  
    }
    @Test
    public void testKo(){
        Ko warunek = new Ko();
        Plansza p = new Plansza();

        assertTrue((warunek.sprawdzPoprawnosc(p, 0, 0, "Bialy")));

        //plansza jest pusta i wykonano ruch wczesniej
        assertTrue((warunek.sprawdzPoprawnosc(p, 0, 0, "Bialy")));

        //Sprawdzamy czy sprawdzanie warunku nie dodaje pionka
        IPoprawnosc warunek2 = new NachodzacePionki();
        assertTrue(warunek2.sprawdzPoprawnosc(p, 0, 0, "Czarny"));

        //tworzymy kształt ko 
        p.dodajPionek("Biały", 1, 0);
        p.dodajPionek("Biały", 0, 1);
        p.dodajPionek("Biały", 1, 2);
        p.dodajPionek("Czarny", 2, 0);
        p.dodajPionek("Czarny", 3, 1);
        p.dodajPionek("Czarny", 2, 2);

        assertTrue(warunek.sprawdzPoprawnosc(p, 2, 1, "Biały"));
        p.dodajPionek("Biały", 2, 1);

        assertTrue(warunek.sprawdzPoprawnosc(p, 1, 1, "Czarny"));
        p.dodajPionek("Czarny", 1, 1);
        p.usunPionek(2, 1);
        p.podajPola().get(19*1+1).podajKamien().ustawOddechy(1);
        p.podajPola().get(2).podajKamien().ustawOddechy(2);

        assertTrue(!warunek.sprawdzPoprawnosc(p, 2, 1, "Biały"));
    }

    @Test
    public void TestOddechy(){
        IPoprawnosc warunek = new PoprawnaLiczbaOddechow();
        IPlansza plansza = new Plansza();
        
        //kiedy są puste pola wokól  to można tam wstawić pionek
        assertTrue(warunek.sprawdzPoprawnosc(plansza, 0, 0, "Biały"));

        plansza.dodajPionek("Czarny", 1, 0);
        plansza.dodajPionek("Czarny", 0, 1);

        //kiedy wokól same czarne pionki to nie można dodać tam pionka bo miałby 0 oddechów
        assertTrue(!warunek.sprawdzPoprawnosc(plansza, 0, 0, "Biały"));

        plansza.dodajPionek("Biały", 1, 2);
        plansza.dodajPionek("Biały", 2, 1);

        //chociaż pionek jest otoczony to można go tu położyć bo tworzy wtedy grupę a grupa ma wystarczająco oddechów
        assertTrue(warunek.sprawdzPoprawnosc(plansza, 1, 1, "Biały"));

        plansza.dodajPionek("Biały", 0, 2);
        plansza.dodajPionek("Biały", 2, 0);
        plansza.dodajPionek("Biały", 1, 1);

        //teraz można bo czarne pionki są wtedy uduszone
        assertTrue(warunek.sprawdzPoprawnosc(plansza, 0, 0, "Biały"));

        //sprawdzamy czy nie da się udusić własnego pionka
        IPlansza p = new Plansza();

        //stawiamy biały pionek
        p.dodajPionek("Biały", 0, 0);

        //otaczamy go ale zostawiamy jedno wolne miejsce
        p.dodajPionek("Czarny", 2, 0);
        p.dodajPionek("Czarny", 1, 1);
        p.dodajPionek("Czarny", 0, 1);

        //sprawdzamy czy da się włożyć pionek w wolne miejsce
        assertTrue(!warunek.sprawdzPoprawnosc(p, 1, 0, "Biały"));
    }
}

