package com.go;

import java.util.ArrayList;

public class GrupaKamieni implements IKamien {

    private ArrayList<IKamien> kamienie = new ArrayList<>();

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

    public IKamien polacz(ArrayList<IKamien> listakamieni){

         //obliczmy oddechy
        int oddechy = 0;
        for(IKamien kamien : listakamieni){
            oddechy = oddechy + kamien.podajOddechy();
        }

         //dodajmy kamienie do listy
        for(IKamien kamien : listakamieni){
            kamienie.add(kamien);
        }

        ustawOddechy(oddechy);

        return this;
    }

    public void usunKamien(IPlansza plansza){
        //usuwamy wszyskie kamienie z grupy z ich pól
        for(IKamien kamien : kamienie){
            kamien.usunKamien(plansza);
        }
    }
    public IKamien podajKamien(){
        return this;
    }

    public void ustawKamien(IKamien dobrykamien){
        //dla każdego kamienia z listy ustawiamy tą GrupęKamieni jako kamien
        for(IKamien kamien : kamienie){
            if(kamien instanceof Kamien){
                kamien.podajPole().ustawKamien(dobrykamien);
            }
            else{
                kamien.ustawKamien(dobrykamien);
            }
        }
    }
    public IPole podajPole(){
        return null;
    }

    public void ustawKolor(String kolor){
        
    }
    public void dodajKamien(String kolor,IPole pole, IPole gora, IPole dol, IPole prawy, IPole lewy){
        
    }
    public void ustawPole(IPole pole){
    }
}
