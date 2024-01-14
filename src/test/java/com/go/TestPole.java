package com.go;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TestPole {
    @Test
    public void testPole()
    {
        IPole p = new Pole();
        IPole gora = new Pole();
        IPole dol = new Pole();
        IPole prawy = new Pole();
        IPole lewy = null;

        prawy.ustawKamien(new Kamien());
        prawy.podajKamien().ustawKolor("Biały");
        prawy.podajKamien().ustawOddechy(3);

        //Sprawdzamy czy zwraca dobry pionek jak się go utworzy ale nie doda jeszcze koloru
        assertSame(null, p.podajPionek());

        p.dodajPionek("Czarny",gora, dol, prawy, lewy);

        //Sprawdzamy czy dodaje dobrze kolor
        assertSame("Czarny", p.podajPionek());

        //Sprawdzamy czy dobrze ustawia się liczba oddechów 
        assertSame(2, p.podajKamien().podajOddechy());

        //Sprawdzamy czy oddechy się odejmuja
        assertSame(2, prawy.podajKamien().podajOddechy());

    }
}