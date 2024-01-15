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

    //TODO zrobić z usuwaniem
    //najlepiej sprawdzic czy jakiś obok ma 1 oddech
    // wtedy nie sprawdzać go w forze
    //ale sprawdzic czy to przeciwnik

    public boolean sprawdzPoprawnosc(IPlansza plansza, int x, int y, String kolor){

        boolean s = true;

        //jeżeli było mniej niż dwa ruchy to niemożliwe jest ko
        if( !(planszaDwaRuchyWczesniej == null)){
            s = false;

            List<IPole> wczesniej = planszaDwaRuchyWczesniej.podajPola();
            List<IPole> teraz = plansza.podajPola();

            //sprawdzamy czy pionek który dodajemy znajdował się na planszy wczesniej
            int miejsceDodanegoPionka = 19*y + x;

            //jeżeli miejsce było puste lub był tam pionek przeciwnika to niemożliwe jest ko
            if((wczesniej.get(miejsceDodanegoPionka).podajPionek() == null ) || !(wczesniej.get(miejsceDodanegoPionka).podajPionek().equals(kolor))){
                s = true;
            }

            for(int i = 0; i < 361; i++){
                //miejsce gdzie dodajemy pionek się różni bo jeszcze go nie dodaliśmy więc to miesce pomijamy
                if(i == miejsceDodanegoPionka){
                    i++;
                }

                //sprawdzamy czy wszystkie miejsca są takie same
                //jeżeli nie są to niemożliwe jest ko
                if(!(wczesniej.get(i).podajPionek() == null && teraz.get(i).podajPionek() == null) || 
                !(wczesniej.get(i).podajPionek() != null && wczesniej.get(i).podajPionek() != null &&
                (wczesniej.get(i).podajPionek().equals(teraz.get(i).podajPionek()))) )
                {
                    s = true;
                }
            }
        }

        planszaDwaRuchyWczesniej = planszaRuchWcześniej;
        planszaRuchWcześniej = plansza;
        return s;
    }
}
