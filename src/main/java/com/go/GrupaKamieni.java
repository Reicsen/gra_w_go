package com.go;

import java.util.ArrayList;

public class GrupaKamieni implements IKamien {

    ArrayList<IKamien> kamienie = new ArrayList<>();

    public void ustawOddechy(int oddechy)
    {
        for(IKamien kamien : kamienie){
            kamien.ustawOddechy(oddechy);
        }
    }
    public int podajOddechy (){
        return kamienie.get(0).podajOddechy();
    }

    public String podajKolor(){
        return kamienie.get(0).podajKolor();
    }

    public IKamien dodajKamien(String kolor, IPole gora, IPole dol, IPole prawy, IPole lewy){
        return this;
    }

    public IKamien polacz(IKamien dodawany, IKamien drugi){
        return this;
    }

    public void usunKamien(){


    }
    public IKamien podajKamien(){
        return this;
    }
    
    public void ustawKolor(String kolor){
        
    }
}
