package com.go;

public class Kamien implements IKamien{

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

    public IKamien dodajKamien(String kolor, IPole gora, IPole dol, IPole prawy, IPole lewy){
        this.kolor = kolor;
        int tempOddechy = 0;

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
            (gora.podajPionek() == kolor || dol.podajPionek() == kolor || prawy.podajPionek() == kolor || lewy.podajPionek() == kolor)){
            IKamien temp = this;
            if(gora.podajPionek() == kolor){
                temp = temp.polacz(temp, gora.podajKamien());
            }
            if(dol.podajPionek() == kolor){
                temp = temp.polacz(temp, dol.podajKamien());
            }
            if(prawy.podajPionek() == kolor){
                temp = temp.polacz(temp, prawy.podajKamien());
            }
            if(lewy.podajPionek() == kolor){
                temp = temp.polacz(temp, lewy.podajKamien());
            }
            return temp;
        }

        return this;
    }
    public IKamien polacz(IKamien dodawany, IKamien drugi){
        return new GrupaKamieni();
    }
    public void usunKamien(){

    }
    public IKamien podajKamien(){
        return this;
    }
    
}
