package com.go;

import java.util.ArrayList;
import java.util.List;

import com.go.Poprawnosc.IPoprawnosc;
import com.go.Poprawnosc.Ko;
import com.go.Poprawnosc.NachodzacePionki;
import com.go.Poprawnosc.PoprawnaLiczbaOddechow;

public class Plansza implements IPlansza
{
    protected List<IPole> pola; //lista 361 pól

    private IPoprawnosc warunekPierwszy = new NachodzacePionki();
    private IPoprawnosc warunekDrugi = new PoprawnaLiczbaOddechow();
    private IPoprawnosc warunekTrzeci = new Ko();

    public Plansza() //konstruktor; metody opisane w interfejsie
    {
        this.pola = new ArrayList<IPole>();
        for (int i=0;i<361;i++)
        {
            IPole temp = new Pole(i%19, i/19);
            pola.add(temp);
        }
    }

    public void dodajPionek(String kolor, int x, int y)
    {
        IPole temp = pola.get(19*y+x);
        IPole gora = null;
        IPole dol = null;
        IPole prawy = null;
        IPole lewy = null;
        if(y > 0){
            gora = pola.get(19*(y-1)+x);
        }
        if(y < 18){
            dol = pola.get(19*(y+1)+x);
        }
        if(x > 0){
            lewy = pola.get(19*y + (x-1));
        }
        if(x < 18){
            prawy = pola.get(19*y + (x+1));
        }
        
        temp.dodajPionek(kolor, gora, dol, prawy, lewy);
    }

    public void usunPionek(int x, int y)
    {
        IPole temp = pola.get(19*y+x);
        temp.usunPionek( this);
    }

    public boolean sprawdzPoprawnosc(String kolor, int x, int y)
    {
        if(warunekPierwszy.sprawdzPoprawnosc(this, x, y, kolor) && warunekDrugi.sprawdzPoprawnosc(this, x, y, kolor) && warunekTrzeci.sprawdzPoprawnosc(this, x, y, kolor)){
            return true;
        }
        return false;
    }
    public List<IPole> podajPola(){
        return pola;
    }
}
