package com.go;

import java.util.ArrayList;

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
    public void dodajKamien(String kolor, IPole pole, IPole gora, IPole dol, IPole prawy, IPole lewy);

    /*
     * usuwamy dany Kamien lub GrupeKamieni
     */
    public void usunKamien(IPlansza plansza);

    /*
     * zwracamy dany IKamien
     */
    public IKamien podajKamien();

    /*
     * Łączymy dane IKamienie w jeden IKamień
     */
    public IKamien polacz(ArrayList<IKamien> kamienie);

    public void ustawKolor(String kolor);

    public void ustawKamien(IKamien kamien);

    public IPole podajPole();

    public void ustawPole(IPole pole);
    
} 
