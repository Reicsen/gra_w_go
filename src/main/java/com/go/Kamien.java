package com.go;

import java.util.ArrayList;

public class Kamien implements IKamien{

    private IPole pole;

    private int oddechy;

    private String kolor;

    public void ustawOddechy(int oddechy)
    {
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

    public void ustawPole(){
        pole.ustawKamien(this);
    }
    public IPole podajPole(){
        return pole;
    }

    public void dodajKamien(String kolor, IPole pole, IPole gora, IPole dol, IPole prawy, IPole lewy){
        this.kolor = kolor;
        int tempOddechy = 0;
        this.pole = pole;

        //Jeżeli pole istnieje 
        //i jest puste to zwiększamy oddechy o 1 
        //a jeżeli jest na nim pionek przeciwnika to odejmujemy mu 1 oddech
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
        oddechy = tempOddechy;

        //Jeżeli kamień położymy koło innego kamienia o tym samym kolorze to tworzą one grupę kamieni
        if((gora != null && dol != null && prawy != null && lewy != null ) &&
            (gora.podajPionek().equals(kolor) || dol.podajPionek().equals(kolor) || prawy.podajPionek().equals(kolor) || lewy.podajPionek().equals(kolor))){
            
            IKamien temp = this;

            //lista do której wrzucamy kamienie lub grupy kamieni które chcemy połączyć w jedną grupę
            ArrayList<IKamien> kamienie = new ArrayList<>();

            if(gora.podajPionek().equals(kolor)){
                kamienie.add(gora.podajKamien());
            }

            if(dol.podajPionek().equals(kolor)){

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
            if(prawy.podajPionek().equals(kolor)){

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
            if(lewy.podajPionek().equals(kolor)){
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
            temp.ustawPole();
        }

    }
    public IKamien polacz(ArrayList<IKamien> kamienie){

        //tworzymy nową grupę kamieni
        GrupaKamieni temp = new GrupaKamieni();
        
        temp.polacz(kamienie);

        return temp;
    }
    public void usunKamien(){
        
    }
    public IKamien podajKamien(){
        return this;
    }
}
