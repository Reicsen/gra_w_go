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

        assertTrue((warunek.sprawdzPoprawnosc(p, 0, 0, "Biały")));

        //plansza jest pusta i wykonano ruch wczesniej
        assertTrue((warunek.sprawdzPoprawnosc(p, 0, 0, "Biały")));

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

        assertSame(0, p.podajPola().get(20).podajKamien().podajOddechy());
        assertSame(0, p.podajPola().get(21).podajKamien().podajOddechy());
        assertSame(warunek.podajPlansze().get(21), p.podajPola().get(21).podajPionek());

        p.usunPionek(2, 1);
        assertSame(1, p.podajPola().get(20).podajKamien().podajOddechy());

        assertSame(warunek.podajPlansze().get(0), p.podajPola().get(0).podajPionek());
        assertSame("Czarny", p.podajPola().get(20).podajPionek());

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

        //sprawdzamy czy da się położyć w miejscu gdzie nie ma oddechu aby udusić przeciwnika
        IPlansza plansza2 = new Plansza();
        plansza2.dodajPionek("Biały", 2, 2);
        plansza2.dodajPionek("Biały", 2, 4);
        plansza2.dodajPionek("Biały", 1, 3);
        plansza2.dodajPionek("Biały", 3, 3);

        plansza2.dodajPionek("Czarny", 1, 2);
        plansza2.dodajPionek("Czarny", 2, 1);
        plansza2.dodajPionek("Czarny", 3, 2);
        plansza2.dodajPionek("Czarny", 4, 3);
        plansza2.dodajPionek("Czarny", 3, 4);
        plansza2.dodajPionek("Czarny", 2, 5);
        plansza2.dodajPionek("Czarny", 1, 4);
        plansza2.dodajPionek("Czarny", 0, 3);

        assertTrue(warunek.sprawdzPoprawnosc(plansza2, 2, 3, "Czarny"));

        //sprawdzamy dla grup
        IPlansza plansza3 = new Plansza();
        plansza3.dodajPionek("Biały", 3, 1);
        plansza3.dodajPionek("Biały", 2, 2);
        plansza3.dodajPionek("Biały", 4, 2);
        plansza3.dodajPionek("Biały", 2, 3);
        plansza3.dodajPionek("Biały", 3, 3);

        plansza3.dodajPionek("Czarny", 3, 0);
        plansza3.dodajPionek("Czarny", 2, 1);
        plansza3.dodajPionek("Czarny", 4, 1);
        plansza3.dodajPionek("Czarny", 1, 2);
        plansza3.dodajPionek("Czarny", 5, 2);
        plansza3.dodajPionek("Czarny", 1, 3);
        plansza3.dodajPionek("Czarny", 4, 3);
        plansza3.dodajPionek("Czarny", 2, 4);
        plansza3.dodajPionek("Czarny", 3, 4);

        assertSame(2,plansza3.podajPola().get(19*2+2).podajKamien().podajOddechy() );

        assertTrue(!warunek.sprawdzPoprawnosc(plansza3, 3, 2, "Biały"));

        assertSame(2,plansza3.podajPola().get(19*2+2).podajKamien().podajOddechy() );

        assertTrue(warunek.sprawdzPoprawnosc(plansza3, 3, 2, "Czarny"));

        assertSame(2,plansza3.podajPola().get(19*2+2).podajKamien().podajOddechy() );

        plansza3.dodajPionek("Czarny", 3, 2);

        assertSame(0,plansza3.podajPola().get(19*2+2).podajKamien().podajOddechy() );

    }
}

