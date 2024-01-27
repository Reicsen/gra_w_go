package com.go;

import java.util.List;
import java.util.ArrayList;

public class ObslugaObszaru implements IObslugaObszaru
{
    public List <Integer> pionyDoUsuniecia(List <Integer> terytorium, IPlansza plansza, String kolor ){
        
        List<Integer> piony = new ArrayList<>();

        for(Integer nrpola : terytorium){

            int x = nrpola % 19;
            int y = nrpola / 19;

            if(y > 0 ){

                IPole gora = plansza.podajPola().get(19*(y-1)+x);

                if(gora.podajPionek() != null && !gora.podajPionek().equals(kolor)){
                    piony.add(19*(y-1)+x);
                }
            }
            if(y < 18){
                IPole dol = plansza.podajPola().get(19*(y+1)+x);

                if(dol.podajPionek() != null && !dol.podajPionek().equals(kolor)){
                    piony.add(19*(y+1)+x);
                }
            }
            if(x > 0){
                IPole lewy = plansza.podajPola().get(19*y + (x-1));

                if(lewy.podajPionek() != null && !lewy.podajPionek().equals(kolor)){
                    piony.add(19* y + (1 - x));
                }
            }
            if(x < 18){
                IPole prawy = plansza.podajPola().get(19*y + (x+1));

                if(prawy.podajPionek() != null && !prawy.podajPionek().equals(kolor)){
                    piony.add(19* y + (1 + x));
                }
            }

        }
        return piony;
    }

    public int iloscPionkowKoloru(IPlansza plansza, String kolor)
    {
        int licznik=0;
        List<IPole> wszystkiePola = plansza.podajPola();
        for (int i=0; i<wszystkiePola.size(); i++)
        {
            IPole pole = wszystkiePola.get(i);
            if (pole.podajPionek()==kolor)
            {
                licznik=licznik+1;
            }
        }
        return licznik;
    }
}
