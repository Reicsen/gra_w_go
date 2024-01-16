package com.go.Poprawnosc;

import com.go.IPlansza;

public class PoprawnaLiczbaOddechow implements IPoprawnosc {
    public boolean sprawdzPoprawnosc(IPlansza plansza, int x, int y, String kolor){
        
        boolean n = false;

        //jeżeli któryś z pól otaczających pole (x,y) istnieje i jest puste to pole(x,y) ma wystarczająca liczbe oddechów
        if(y > 0 && plansza.podajPola().get(19 * (y-1) + x).podajPionek() == null){
            n = true;
        }
        else if(y < 18 && plansza.podajPola().get(19 * (y+1) + x).podajPionek() == null){
            n = true;
        }
        else if(x > 0 && plansza.podajPola().get(19 * y + (x-1)).podajPionek() == null){
            n = true;
        }
        else if(x < 18 && plansza.podajPola().get(19* y + (x + 1)).podajPionek() == null){
            n = true;
        }

        //jeżeli obok są jego własne pionki to ich oddechy się sumują
        else if(y > 0 && plansza.podajPola().get(19 * (y-1) + x).podajPionek().equals(kolor) && plansza.podajPola().get(19 * (y-1) + x).podajKamien().podajOddechy() > 1){
            n = true;
        }
        else if(y < 18 && plansza.podajPola().get(19 * (y+1) + x).podajPionek().equals(kolor) && plansza.podajPola().get(19 * (y+1) + x).podajKamien().podajOddechy() > 1){
            n = true;
        }
        else if(x > 0 && plansza.podajPola().get(19 * y + (x-1)).podajPionek().equals(kolor) && plansza.podajPola().get(19 * y + (x-1)).podajKamien().podajOddechy() > 1){
            n = true;
        }
        else if(x < 18 && plansza.podajPola().get(19* y + (x + 1)).podajPionek().equals(kolor) && plansza.podajPola().get(19 * y + (x+1)).podajKamien().podajOddechy() > 1){
            n = true;
        }

        //wyjątek: duszenie przeciwnika
        else if(y > 0 && !plansza.podajPola().get(19 * (y-1) + x).podajPionek().equals(kolor) && plansza.podajPola().get(19 * (y-1) + x).podajKamien().podajOddechy() == 1){
            n = true;
        }
        else if(y < 18 && !plansza.podajPola().get(19 * (y+1) + x).podajPionek().equals(kolor) && plansza.podajPola().get(19 * (y+1) + x).podajKamien().podajOddechy() == 1){
            n = true;
        }
        else if(x > 0 && !plansza.podajPola().get(19 * y + (x-1)).podajPionek().equals(kolor) && plansza.podajPola().get(19 * y + (x-1)).podajKamien().podajOddechy() == 1){
            n = true;
        }
        else if(x < 18 && !plansza.podajPola().get(19* y + (x + 1)).podajPionek().equals(kolor) && plansza.podajPola().get(19 * y + (x+1)).podajKamien().podajOddechy() == 1){
            n = true;
        }

        return n;
    }
}
