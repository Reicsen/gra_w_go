package com.go;
/**
 * IKamien
 * Composite Design Pattern
 */
public interface IKamien {
    
    /*
     * ustawiamy oddechy
     */
    public void ustawOddechy(int oddechy);
    /*
     * zwracamy liczbę oddechów
     */
    public int podajOddechy();

    /*
     * podajemy jaki kolor ma dany kamien lub grupa kamieni
     */
    public String podajKolor();

    /*
     * zwracamy IKamien który ma ustawiony odpowiedni kolor oraz liczbę oddechów
     * zwracamy Kamien lub GrupeKamieni
     */
    public IKamien dodajKamien(String kolor, IPole gora, IPole dol, IPole prawy, IPole lewy);

    /*
     * usuwamy dany Kamien lub GrupeKamieni
     */
    public void usunKamien();

    /*
     * zwracamy dany IKamien
     */
    public IKamien podajKamien();

    /*
     * Łączymy dane IKamienie w jeden IKamień
     */
    public IKamien polacz(IKamien dodawany, IKamien drugi);

    public void ustawKolor(String kolor);
    
} 
