package com.go;

public class Pole implements IPole
{
    private IKamien kamien;

    public Pole() //konstruktor; metody opisane w interfejsie
    {
        this.kamien=new Kamien();
    }

    public String podajPionek()
    {
        return kamien.podajKolor();
    }

    public void dodajPionek(String kolor)
    {
        kamien.dodajKamien(kolor);
    }

    public void usunPionek()
    {
        kamien.usunKamien();
    }
}