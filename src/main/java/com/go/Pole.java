package com.go;

public class Pole implements IPole
{
    private IKamien kamien;

    public Pole() //konstruktor; metody opisane w interfejsie
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
        IKamien temp = new Kamien();
        kamien = temp.dodajKamien(kolor, gora, dol, prawy, lewy);
    }

    public void usunPionek()
    {
        kamien.usunKamien();
    }

    public IKamien podajKamien(){
        return kamien;
    }
    public void ustawKamien(IKamien kamien){
        this.kamien = kamien;
    }
}