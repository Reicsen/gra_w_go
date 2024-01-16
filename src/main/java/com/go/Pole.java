package com.go;

public class Pole implements IPole
{
    private IKamien kamien;
    private int x;
    private int y;

    public Pole(int x, int y) //konstruktor
    {
        this.kamien=null;
        this.x = x;
        this.y = y;
    }

    public int podajX(){
        return x;
    }
    public int podajY(){
        return y;
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

    public void usunPionek( IPlansza plansza)
    {
        kamien.usunKamien(plansza);
    }

    public IKamien podajKamien(){
        return kamien;
    }
    public void ustawKamien(IKamien kamien){
        this.kamien = kamien;
    }
    public void ustawPole(){
        kamien.ustawPole(this);
    }
}