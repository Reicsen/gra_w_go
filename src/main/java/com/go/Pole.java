package com.go;

public class Pole implements IPole
{
    private String pionek;

    public Pole()
    {
        this.pionek=null;
    }

    public Pole(String kolor)
    {
        this.pionek=kolor;
    }

    public String podajPionek()
    {
        return this.pionek;
    }

    public void dodajPionek(String kolor)
    {
        this.pionek=kolor;
    }
}