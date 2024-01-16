package com.go;

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
}
