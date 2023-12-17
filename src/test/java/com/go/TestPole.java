package com.go;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TestPole {
    IPole p;
    @Test
    public void testPole()
    {
        p = new Pole();

        //Sprawdzamy czy zwraca dobry pionek jak się go utworzy ale nie doda jeszcze koloru
        assertSame(null, p.podajPionek());

        p.dodajPionek("Czarny");

        //Sprawdzamy czy dodaje dobrze kolor
        assertSame("Czarny", p.podajPionek());

    }
}