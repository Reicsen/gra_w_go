package com.go;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TestPlansza {
    IPlansza p;
    @Test
    public void testPlansza(){
        //Tworzymy nową plansze
        p = new Plansza();

        //Sprawdzamy czy możemy dosatwić pionek w puste miejsce
        if(p.sprawdzPoprawnosc("Czarny", 12, 7)){
            assert false;
        }

        p.dodajPionek("Czarny", 12, 7);

        //Sprawdzamy czy nie można dostawić drugiego w to samo miejsce
        if(p.sprawdzPoprawnosc("Czarny", 12, 7)){
            assert false;
        }
    }
    
}
