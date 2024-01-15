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

    public void dodanieKamienia(ArrayList<IKamien> kamienie){

        //jak dodajemy kolejny kamień to zmieniamy liczbę oddechów

        //dodajemy kamien do listy
    }

    public void dodajKamien(String kolor,IPole pole, IPole gora, IPole dol, IPole prawy, IPole lewy){
        
    }

    public IKamien polacz(ArrayList<IKamien> kamienie){
        //TODO
        return this;
    }

    public void usunKamien(){
        //usuwamy wszyskie kamienie z grupy z ich pól
        for(IKamien kamien : kamienie){
            kamien.podajPole().usunPionek();
        }
    }
    public IKamien podajKamien(){
        return this;
    }

    public void ustawPole(){
        //dla każdego kamienia z listy ustawiamy tą GrupęKamieni jako kamien
        for(IKamien kamien : kamienie){
            kamien.podajPole().ustawKamien(this);
        }
    }
    public IPole podajPole(){
        return null;
    }

    public void ustawKolor(String kolor){
        
    }
}
