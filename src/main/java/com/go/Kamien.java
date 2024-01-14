package com.go;

public class Kamien implements IKamien{

    private int oddechy;

    private String kolor;

    public void ustawOddechy(int oddechy)
    {
        oddechy = oddechy;
    }
    public int podajOddechy (){
        return oddechy;
    }

    public String podajPionek(){
        return kolor;
    }
    
}
