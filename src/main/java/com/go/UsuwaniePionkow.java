package com.go;

import java.util.ArrayList;

public class UsuwaniePionkow implements IUsuwaniePionkow{

    public int obliczanieJencow(IPlansza plansza){
        int jency = 0;
        for(IPole pole : plansza.podajPola()){
            if(pole.podajKamien()!=null && pole.podajKamien().podajOddechy() == 0){
                jency++;
            }
        }
        return jency;
    }
    
    public ArrayList<Integer> pionkiDoUsuniecia(IPlansza plansza){
        ArrayList<Integer> pionki = new ArrayList<>();

        for(int i = 0; i < 361; i++){
            IPole pole = plansza.podajPola().get(i);
            if(pole.podajKamien() != null && pole.podajKamien().podajOddechy() == 0 ){
                pionki.add(i);
            }
        }

        return pionki;
        
    }

    public void usunPionki(IPlansza plansza){
        for(int i = 0; i < 361; i++){
            IPole pole = plansza.podajPola().get(i);
            if(pole.podajKamien() != null && pole.podajKamien().podajOddechy() == 0 ){
                plansza.usunPionek(i%19, i/19);
            }
        }
    }
}
