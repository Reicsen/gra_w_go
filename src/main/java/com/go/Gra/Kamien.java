package com.go.Gra;

import java.util.ArrayList;

public class Kamien implements IKamien{

    private IPole pole;

    private int oddechy;

    private String kolor;

    public void ustawOddechy(int oddechy){
        this.oddechy = oddechy;
    }

    public int podajOddechy (){
        return oddechy;
    }

    public String podajKolor(){
        return kolor;
    }

    public void ustawKolor(String kolor){
        this.kolor = kolor;
    }

    public void ustawKamien(IKamien kamien){
        pole.ustawKamien(kamien);
    }

    public IPole podajPole(){
        return pole;
    }

    public void ustawPole(IPole pole){
        this.pole = pole;
    }
    public void dodajKamien(String kolor, IPole pole, IPole gora, IPole dol, IPole prawy, IPole lewy){

        this.kolor = kolor;
        this.pole = pole;

        int tempOddechy = 0;

        //Jeżeli pole istnieje 
        //i jest puste to zwiększamy nasze oddechy o 1 
        //a jeżeli jest na nim pionek to odejmujemy mu 1 oddech
        if(gora != null ){
            if(gora.podajPionek()==null){
                tempOddechy++;
            }
            else {
                gora.podajKamien().ustawOddechy(gora.podajKamien().podajOddechy() - 1);
            }
        }
        if(dol != null ){
            if(dol.podajPionek()==null){
                tempOddechy++;
            }
            else {
                dol.podajKamien().ustawOddechy(dol.podajKamien().podajOddechy() - 1);
            }
        }
        if(prawy != null){
            if(prawy.podajPionek()==null){
                tempOddechy++;
            }
            else {
                prawy.podajKamien().ustawOddechy(prawy.podajKamien().podajOddechy() - 1);
            }
        }
        if(lewy != null){
            if(lewy.podajPionek()==null){
                tempOddechy++;
            }
            else {
                lewy.podajKamien().ustawOddechy(lewy.podajKamien().podajOddechy() - 1);
            }
        }
        //ustawiamy oddechy
        oddechy = tempOddechy;

        //Jeżeli kamień położymy koło innego kamienia o tym samym kolorze to tworzą one grupę kamieni
        if( (gora != null && !(gora.podajPionek()==null) && gora.podajPionek().equals(kolor)) ||
        (dol != null && !(dol.podajPionek()==null) && dol.podajPionek().equals(kolor)) ||
        (prawy != null && !(prawy.podajPionek()==null) && prawy.podajPionek().equals(kolor)) || 
        (lewy != null && !(lewy.podajPionek()==null) && lewy.podajPionek().equals(kolor))){

            IKamien temp = this;

            //lista do której wrzucamy kamienie lub grupy kamieni które chcemy połączyć w jedną grupę
            ArrayList<IKamien> kamienie = new ArrayList<>();

            if(gora != null  && !(gora.podajPionek()==null) && gora.podajPionek().equals(kolor)){
                kamienie.add(gora.podajKamien());
            }

            if(dol != null && !(dol.podajPionek()==null) && dol.podajPionek().equals(kolor)){

                if(kamienie.size() > 0){

                    //sprawdzamy czy nie dodajemy tego samego 
                    if( !(kamienie.get(0) == dol.podajKamien()) ){
                        kamienie.add(dol.podajKamien());
                    }
                }
                else{
                    kamienie.add(dol.podajKamien());
                }
            }
            if(prawy != null && !(prawy.podajPionek()==null) && prawy.podajPionek().equals(kolor)){

                if(kamienie.size() == 0){
                    kamienie.add(prawy.podajKamien());
                }
                else{
                    boolean n = true;
                    for(IKamien kamien : kamienie){
                        if(prawy.podajKamien() == kamien){
                            n = false;
                        }
                    }
                    if(n == true){
                        kamienie.add(prawy.podajKamien());
                    }
                }
            }
            if(lewy != null && !(lewy.podajPionek()==null) && lewy.podajPionek().equals(kolor)){
                if(kamienie.size() == 0){
                    kamienie.add(lewy.podajKamien());
                }
                else{
                    boolean n = true;
                    for(IKamien kamien : kamienie){
                        if(lewy.podajKamien() == kamien){
                            n = false;
                        }
                    }
                    if(n == true){
                        kamienie.add(lewy.podajKamien());
                    }
                }
            }

            kamienie.add(this);

            temp = temp.polacz(kamienie);

            // pole na którym jest nasz kamień nie ma już Kamienia a jest GrupaKamieni więc to zmieniamy
            temp.ustawKamien(temp);
        }

    }
    public IKamien polacz(ArrayList<IKamien> kamienie){

        //tworzymy nową grupę kamieni
        IKamien temp = new GrupaKamieni();
        
        temp = temp.polacz(kamienie);

        return temp;
    }
    public void usunKamien(IPlansza plansza){

        if(podajPole().podajY() > 0 && plansza.podajPola().get(19*(podajPole().podajY()-1) + podajPole().podajX()).podajKamien() != null){
            IPole gora = plansza.podajPola().get(19*(podajPole().podajY()-1) + podajPole().podajX());
            gora.podajKamien().ustawOddechy(gora.podajKamien().podajOddechy()+1);
        }
        if(podajPole().podajY() < 18 && plansza.podajPola().get(19*(podajPole().podajY()+1) + podajPole().podajX()).podajKamien() != null){
            IPole dol = plansza.podajPola().get(19*(podajPole().podajY()+1)+ podajPole().podajX());
            dol.podajKamien().ustawOddechy(dol.podajKamien().podajOddechy()+1);
        }
        if(podajPole().podajX() > 0 && plansza.podajPola().get(19*podajPole().podajY() + (podajPole().podajX() -1)).podajKamien() != null){
            IPole lewy = plansza.podajPola().get(19*podajPole().podajY() + (podajPole().podajX()-1));
            lewy.podajKamien().ustawOddechy(lewy.podajKamien().podajOddechy()+1);
        }
        if(podajPole().podajX() < 18 && plansza.podajPola().get(19*podajPole().podajY() + (podajPole().podajX() + 1)).podajKamien() != null){
            IPole prawy = plansza.podajPola().get(19*podajPole().podajY() + (podajPole().podajX()+1));
            prawy.podajKamien().ustawOddechy(prawy.podajKamien().podajOddechy()+1);
        }
        
        pole.ustawKamien(null);
    }
    public IKamien podajKamien(){
        return this;
    }
}
