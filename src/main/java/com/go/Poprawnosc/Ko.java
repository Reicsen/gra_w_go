package com.go.Poprawnosc;

import java.util.List;

import com.go.IPlansza;
import com.go.IPole;
import com.go.Plansza;

public class Ko implements IPoprawnosc{
    IPlansza planszaRuchWcześniej;
    IPlansza planszaDwaRuchyWczesniej;

    public Ko(){
        planszaRuchWcześniej = null;
        planszaDwaRuchyWczesniej = null;
    }

    public boolean sprawdzPoprawnosc(IPlansza plansza, int x, int y, String kolor){

        boolean s = true;

        if( !(planszaDwaRuchyWczesniej == null)){
            s = false;

            List<IPole> wczesniej = planszaDwaRuchyWczesniej.podajPola();
            List<IPole> teraz = plansza.podajPola();

            //sprawdzamy czy wszystkie pionki są tak samo kiedy dodamy pionek na x y

            int miejsceDodanegoPionka = 19*y + x;

            for(int i = 0; i < 361; i++){

                if((wczesniej.get(miejsceDodanegoPionka).podajPionek() == null ) || !(wczesniej.get(miejsceDodanegoPionka).podajPionek().equals(kolor))){
                    s = true;
                }

                if(!(wczesniej.get(i).equals(teraz.get(i))) && i != miejsceDodanegoPionka){
                    s = true;
                }
            }

        }

        planszaDwaRuchyWczesniej = planszaRuchWcześniej;
        planszaRuchWcześniej = plansza;
        return s;
    }
}
