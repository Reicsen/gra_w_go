package com.go;

public class Pole implements IPole
{
    private String pionek;

    public Pole()
    {
        this.pionek=null;
    }

<<<<<<< HEAD
    protected String podajPionek()
=======
    public Pole(String kolor)
    {
        this.pionek=kolor;
    }

    public String podajPionek()
>>>>>>> 61bd39640edb6b2090bc220c07614a541c8a6e6c
    {
        return this.pionek;
    }

    public void dodajPionek(String kolor)
    {
        this.pionek=kolor;
    }
}