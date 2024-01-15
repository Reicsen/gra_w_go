package com.go;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.go.Poprawnosc.IPoprawnosc;
import com.go.Poprawnosc.Ko;
import com.go.Poprawnosc.NachodzacePionki;

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
    public void ko(){
        IPoprawnosc warunek = new Ko();
        Plansza p = new Plansza();

        assertTrue((warunek.sprawdzPoprawnosc(p, 0, 0, "Bialy")));
        assertTrue((warunek.sprawdzPoprawnosc(p, 0, 0, "Bialy")));
        assertTrue((warunek.sprawdzPoprawnosc(p, 0, 0, "Bialy")));

        //Sprawdzamy czy sprawdzanie warunku nie dodaje pionka
        IPoprawnosc warunek2 = new NachodzacePionki();
        assertTrue(warunek2.sprawdzPoprawnosc(p, 0, 0, "czarny"));

    }
}

