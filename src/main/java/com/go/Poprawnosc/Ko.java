package com.go.Poprawnosc;

import java.util.ArrayList;

import com.go.IPlansza;
import com.go.IPole;

public class Ko implements IPoprawnosc{
    
    //lista która zawiera stringi (biały lub czarny) lub null tam gdzie na wcześniejszej na planszy były pola
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

            IPole gora = plansza.podajPola().get(19 * (y-1) + x);
            IPole dol = plansza.podajPola().get(19 * (y+1) + x);
            IPole lewy = plansza.podajPola().get(19 * y + (x-1));
            IPole prawy = plansza.podajPola().get(19 * y + (x+1));

            if(y > 0 && !(gora.podajPionek() == null) && !(gora.podajPionek().equals(kolor))){
                if(gora.podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * (y-1) + x;
                }
            }

            if(y < 18 && !(dol.podajPionek() == null) && !(dol.podajPionek().equals(kolor))){
                if(dol.podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * (y+1) + x;
                }
            }

            if(x > 0 && !(lewy.podajPionek() == null) && !(lewy.podajPionek().equals(kolor))){
                if(lewy.podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * y + (x-1);
                }
            }

            if(x < 18 && !(prawy.podajPionek() == null) && !(prawy.podajPionek().equals(kolor))){
                if(prawy.podajKamien().podajOddechy() == 1){
                    miejsceUsuwanegoPionka = 19 * y + (x+1);
                }
            }

            //sprawdzamy czy tam gdzie usuwamy pionek wcześniej było pusto
            //sprawdzamy czy kasujemy ostatnio wstawiony pionek
            
            if( miejsceUsuwanegoPionka == -1 || !(planszaWczesniej.get(miejsceUsuwanegoPionka) == null)){
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
            }
        }
        if(s == true){
            ustawListe(plansza);
        }
        return s;
    }

    private void ustawListe(IPlansza plansza){

        planszaWczesniej = new ArrayList<>();
        for(IPole pole : plansza.podajPola()){
            if(pole.podajPionek() == null){
                planszaWczesniej.add(null);
            }
            else if(pole.podajPionek().equals("biały")){
                planszaWczesniej.add("biały");
            }
            else{
                planszaWczesniej.add("czarny");
            }
        }

    }
    public ArrayList<String> podajPlansze(){
        return planszaWczesniej;
    }
}
