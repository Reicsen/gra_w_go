package com.go;

public class Pole
{
    private String pionek;

    public Pole()
    {
        this.pionek=null;
    }

    protected String podajPionek()
    {
        return this.pionek;
    }

    protected void dodajPionek(String kolor)
    {
        this.pionek=kolor;
    }
}