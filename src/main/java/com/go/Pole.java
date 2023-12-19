package com.go;

public class Pole implements IPole
{
    private String pionek; //pole przechowujące informację o tym, jaki pionek znajduje się obecnie na polu

    public Pole() //konstruktor; metody opisane w interfejsie
    {
        this.pionek=null;
    }

    public String podajPionek()
    {
        return this.pionek;
    }

    public void dodajPionek(String kolor)
    {
        this.pionek=kolor;
    }

    public void usunPionek()
    {
        this.pionek=null;
    }
}