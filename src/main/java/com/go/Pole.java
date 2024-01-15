package com.go;

public class Pole implements IPole
{
    private IKamien kamien;

    public Pole() //konstruktor
    {
        this.kamien=null;
    }

    public String podajPionek()
    {
        if(kamien!=null){
            return kamien.podajKolor();
        }
        else{
            return null;
        }
    }

    public void dodajPionek(String kolor, IPole gora, IPole dol, IPole prawy, IPole lewy)
    {
        kamien = new Kamien();
        kamien.dodajKamien(kolor, this, gora, dol, prawy, lewy);
    }

    public void usunPionek()
    {
        kamien.usunKamien();
        kamien = null;
    }

    public IKamien podajKamien(){
        return kamien;
    }
    public void ustawKamien(IKamien kamien){
        this.kamien = kamien;
    }
}