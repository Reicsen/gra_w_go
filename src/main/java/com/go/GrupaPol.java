package com.go;

import java.util.ArrayList;

public class GrupaPol implements IPole{

    private String pionek; //pole przechowujące informację o tym, jaki pionek znajduje się obecnie na polu
    private int oddechy;

    private ArrayList<IPole> pionki = new ArrayList<>();

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

    public void ustawOddechy(int oddechy)
    {
        this.oddechy = oddechy;
    }
    public int podajOddechy (){
        return oddechy;
    }
}
