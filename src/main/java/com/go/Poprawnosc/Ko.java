package com.go.Poprawnosc;

import java.util.ArrayList;
import java.util.List;

import com.go.IPlansza;
import com.go.IPole;
import com.go.Plansza;

public class Ko implements IPoprawnosc{
    
    ArrayList<String> planszaWczesniej;

    public Ko(){
        planszaWczesniej = new ArrayList<>();
    }

    public boolean sprawdzPoprawnosc(IPlansza plansza, int x, int y, String kolor){

        boolean s = true;

        if(planszaWczesniej.size() > 0){
            s = false;

            int miejsceDodanegoPionka = 19*y + x;

            //sprawdzamy czy chcemy położyć pionek tam gdzie był wcześniej
            if((planszaWczesniej.get(miejsceDodanegoPionka) == null ) || !(planszaWczesniej.get(miejsceDodanegoPionka).equals(kolor))){
                //jeżeli to miejsce wczesniej było null lub był tam pionek przeciwnika to nie naruszamy zasady ko
                s = true;
                ustawListe(plansza);
                return s;
            }

            //szukamy pionka który usuniemy jak położymy nasz pionek w tym miejscu
            int miejsceUsuwanegoPionka = -1;

            if(y > 0 && !(plansza.podajPola().get(19 * (y-1) + x).podajPionek() == null) && !(plansza.podajPola().get(19 * (y-1) + x).podajPionek().equals(kolor))){
                if(plansza.podajPola().get(19 * (y-1) + x).podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * (y-1) + x;
                }
            }

            else if(y < 18 && !(plansza.podajPola().get(19 * (y+1) + x).podajPionek() == null) && !(plansza.podajPola().get(19 * (y+1) + x).podajPionek().equals(kolor))){
                if(plansza.podajPola().get(19 * (y+1) + x).podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * (y+1) + x;
                }
            }

            else if(x > 0 && !(plansza.podajPola().get(19 * y + (x-1)).podajPionek() == null) && !(plansza.podajPola().get(19 * y + (x-1)).podajPionek().equals(kolor))){
                if(plansza.podajPola().get(19 * y + (x-1)).podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * y + (x-1);
                }
            }

            else if(x < 18 && !(plansza.podajPola().get(19* y + (x + 1)).podajPionek() == null) && !(plansza.podajPola().get(19 * y + (x+1)).podajPionek().equals(kolor))){
                if(plansza.podajPola().get(19 * y + (x+1)).podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * y + (x+1);
                }
            }

            //sprawdzamy czy tam gdzie usuwamy pionek wcześniej było pusto
            /*if( miejsceUsuwanegoPionka == -1 || !(planszaWczesniej.get(miejsceUsuwanegoPionka) == null)){
                s = true;
                ustawListe(plansza);
                return s;
            }
            for(int i = 0; i < 361; i++){
                //sprawdzamy czy wszystkie miejsca są takie same oprócz miejca gdzie wstawiamy i gdzie jest usuwany pionek
                if( i != miejsceDodanegoPionka && i != miejsceUsuwanegoPionka &&
                    (!(planszaWczesniej.get(i) == null && plansza.podajPola().get(i).podajPionek() == null) &&
                    !((planszaWczesniej.get(i) != null && plansza.podajPola().get(i).podajPionek() != null) &&
                    ( planszaWczesniej.get(i).equals(plansza.podajPola().get(i).podajPionek())))) )
                {
                    s = true;
                    ustawListe(plansza);
                    return s;
                }
            }*/
        }
        if(s == true){
            ustawListe(plansza);
        }
        return s;
    }

    private void ustawListe(IPlansza plansza){

        planszaWczesniej = new ArrayList<>();
        for(IPole pole : plansza.podajPola()){
            planszaWczesniej.add(pole.podajPionek());
        }

    }
}
