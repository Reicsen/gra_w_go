package com.go.Gra;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class TestPole {
    /*
     * Sprawdzamy czy odpowiednio się ustawiają oddechy i kolory dla pojedyńczych kamieni
     */
    @Test
    public void testPole()
    {
        IPole p = new Pole(0,1);
        IPole gora = new Pole(0,0);
        IPole dol = new Pole(0,2);
        IPole prawy = new Pole(1,1);
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

        //Sprawdzamy czy oddechy się odejmuja jak dodamy obok inny pionek
        assertSame(2, prawy.podajKamien().podajOddechy());

    }
    /*
     * Sprawdzamy czy odpowiednio się ustawiają oddechy dla grup kamieni kamieni
     */
    @Test
    public void testPoleGrupyKamieni(){
        IPole p = new Pole(0,1);
        IPole gora = new Pole(0,0);
        IPole dol = new Pole(0,2);
        IPole prawy = new Pole(1,1);
        IPole lewy = null;

        prawy.ustawKamien(new Kamien());
        prawy.podajKamien().ustawKolor("Biały");
        prawy.podajKamien().ustawOddechy(3);
        prawy.ustawPole();

        gora.ustawKamien(new Kamien());
        gora.podajKamien().ustawKolor("Biały");
        gora.podajKamien().ustawOddechy(3);
        gora.ustawPole();

        p.dodajPionek("Biały",gora, dol, prawy, lewy);

        assertSame(5, p.podajKamien().podajOddechy());
        assertSame(5, gora.podajKamien().podajOddechy());

        assertSame(p.podajKamien(), prawy.podajKamien());
        assertSame(gora.podajKamien(), prawy.podajKamien());

        IPole przeciwnik = new Pole(10,10);
        przeciwnik.dodajPionek("Czarny", gora, dol, prawy, lewy);

        assertSame(3, p.podajKamien().podajOddechy());
        assertSame(1, przeciwnik.podajKamien().podajOddechy());
    }
}