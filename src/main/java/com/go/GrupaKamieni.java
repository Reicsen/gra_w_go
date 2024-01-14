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
        
    }
    
}
