package com.go;

import java.util.List;
import java.util.ArrayList;

public class Terytorium implements ITerytorium {

    public int obliczTerytorium(IPlansza plansza, String kolor){
        ArrayList<Integer> punktyTerytorium = new ArrayList<>();
        int terytorium = 0;
        return 0;
    }

    public List <Integer> pionyNaTerytoriumPrzeciwnika(IPlansza plansza, String kolor){

        ArrayList <Integer> martwePiony = new ArrayList<>();
        
        return martwePiony;
    }

    public int iloscPionowNaTerytoriumPrzeciwnika(IPlansza plansza, String kolor)
    {
        List<Integer> temp = pionyNaTerytoriumPrzeciwnika(plansza,kolor);
        return temp.size();
    }
}
