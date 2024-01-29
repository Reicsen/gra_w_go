package com.go.Poprawnosc;

import com.go.Gra.IPlansza;
import com.go.Gra.IPole;

public class PoprawnaLiczbaOddechow implements IPoprawnosc {
    IPoprawnosc ko;

    public PoprawnaLiczbaOddechow(IPoprawnosc ko){
        this.ko = ko;
    }

    public boolean sprawdzPoprawnosc(IPlansza plansza, int x, int y, String kolor){

        //jeżeli któryś z pól otaczających pole (x,y) istnieje i jest puste to pole(x,y) ma wystarczającą liczbe oddechów
        if(y > 0 && plansza.podajPola().get(19 * (y-1) + x).podajPionek() == null){
            return ko.sprawdzPoprawnosc(plansza, x, y, kolor);
        }
        else if(y < 18 && plansza.podajPola().get(19 * (y+1) + x).podajPionek() == null){
            return ko.sprawdzPoprawnosc(plansza, x, y, kolor);
        }
        else if(x > 0 && plansza.podajPola().get(19 * y + (x-1)).podajPionek() == null){
            return ko.sprawdzPoprawnosc(plansza, x, y, kolor);
        }
        else if(x < 18 && plansza.podajPola().get(19* y + (x + 1)).podajPionek() == null){
            return ko.sprawdzPoprawnosc(plansza, x, y, kolor);
        }

        boolean n = false;

        IPole gora = null;
        IPole dol = null;
        IPole prawy = null;
        IPole lewy = null;

        //odejmujemy 1 oddech od każdego pola które znajduje się wokół pola (x,y)
        if(y > 0){

            gora = plansza.podajPola().get(19*(y-1)+x);

            if(gora.podajKamien() != null){
                gora.podajKamien().ustawOddechy(gora.podajKamien().podajOddechy() - 1);
            }
        }
        if(y < 18){
            dol = plansza.podajPola().get(19*(y+1)+x);

            if(dol.podajKamien() != null){
                dol.podajKamien().ustawOddechy(dol.podajKamien().podajOddechy() - 1);
            }
        }
        if(x > 0){
            lewy = plansza.podajPola().get(19*y + (x-1));

            if(lewy.podajKamien() != null){
                lewy.podajKamien().ustawOddechy(lewy.podajKamien().podajOddechy() - 1);
            }
        }
        if(x < 18){
            prawy = plansza.podajPola().get(19*y + (x+1));

            if(prawy.podajKamien() != null){
                prawy.podajKamien().ustawOddechy(prawy.podajKamien().podajOddechy() - 1);
            }
        }

        //jeżeli obok są jego własne pionki to ich oddechy się sumują
        if(y > 0 && gora.podajPionek() != null && gora.podajPionek().equals(kolor) && gora.podajKamien().podajOddechy() > 0){
            n = true;
        }
        else if(y < 18 && dol.podajPionek() != null && dol.podajPionek().equals(kolor) && dol.podajKamien().podajOddechy() > 0){
            n = true;
        }
        else if(x > 0 && lewy.podajPionek() != null && lewy.podajPionek().equals(kolor) && lewy.podajKamien().podajOddechy() > 0){
            n = true;
        }
        else if(x < 18 && prawy.podajPionek() != null && prawy.podajPionek().equals(kolor) && prawy.podajKamien().podajOddechy() > 0){
            n = true;
        }

        //wyjątek: duszenie przeciwnika
        else if(y > 0 && gora.podajPionek() != null && !gora.podajPionek().equals(kolor) && gora.podajKamien().podajOddechy() == 0){
            n = true;
        }
        else if(y < 18 && dol.podajPionek() != null && !dol.podajPionek().equals(kolor) && dol.podajKamien().podajOddechy() == 0){
            n = true;
        }
        else if(x > 0 && lewy.podajPionek() != null && !lewy.podajPionek().equals(kolor) && lewy.podajKamien().podajOddechy() == 0){
            n = true;
        }
        else if(x < 18 && prawy.podajPionek() != null && !prawy.podajPionek().equals(kolor) && prawy.podajKamien().podajOddechy() == 0){
            n = true;
        }

        //ustawiamy z powrotem prawidłową liczbę oddechów
        if(y > 0){

            if(gora.podajKamien() != null){
                gora.podajKamien().ustawOddechy(gora.podajKamien().podajOddechy() + 1);
            }
        }
        if(y < 18){
            if(dol.podajKamien() != null){  
                dol.podajKamien().ustawOddechy(dol.podajKamien().podajOddechy() + 1);
            }
        }
        if(x > 0){
            if(lewy.podajKamien() != null){
                lewy.podajKamien().ustawOddechy(lewy.podajKamien().podajOddechy() + 1);
            }
        }
        if(x < 18){
            if(prawy.podajKamien() != null){
                prawy.podajKamien().ustawOddechy(prawy.podajKamien().podajOddechy() + 1);
            }
        }

        if(n == true){
            return ko.sprawdzPoprawnosc(plansza, x, y, kolor);
        }
        return n;
    }
}
