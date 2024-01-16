package com.go;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestPlansza {
    IPlansza p;
    @Test
    public void testPlansza(){
        //Tworzymy nową plansze
        p = new Plansza();

        //Sprawdzamy czy możemy dosatwić pionek w puste miejsce
        if(!p.sprawdzPoprawnosc("Czarny", 12, 7)){
            assert false;
        }

        p.dodajPionek("Czarny", 12, 7);

        //Sprawdzamy czy nie można dostawić drugiego w to samo miejsce
        if(p.sprawdzPoprawnosc("Czarny", 12, 7)){
            assert false;
        }

        p.usunPionek(12, 7);
        if(!p.sprawdzPoprawnosc("Czarny", 12, 7)){
            assert false;
        }
        
        p.dodajPionek("Biały", 0, 0);
        p.dodajPionek("Czarny", 1, 0);
        assertTrue(p.podajPola().get(1).podajKamien().podajOddechy() == 2);
        p.usunPionek(0, 0);
        assertTrue(p.podajPola().get(1).podajKamien().podajOddechy() == 3);
        p.dodajPionek("Czarny", 2, 0);
        p.dodajPionek("Czarny", 3, 0);
        assertSame(5, p.podajPola().get(1).podajKamien().podajOddechy());
        assertSame(p.podajPola().get(1).podajKamien(), p.podajPola().get(3).podajKamien());

        p.dodajPionek("Biały", 1, 1);
        p.dodajPionek("Biały", 2, 1);
        p.dodajPionek("Biały", 3, 1);
        assertSame(2, p.podajPola().get(1).podajKamien().podajOddechy());
        assertTrue(p.podajPola().get(20).podajKamien().podajOddechy() == 5);
        p.usunPionek(1, 0);
        assertTrue(p.sprawdzPoprawnosc("Czarny", 3, 0));
        assertSame(8, p.podajPola().get(20).podajKamien().podajOddechy());
    }
    
}
