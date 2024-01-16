package com.go;

public interface IPole {
    String podajPionek(); //metoda zwracająca, jaki pionek teraz jest na danym polu
    void dodajPionek(String kolor, IPole gora, IPole dol, IPole prawy, IPole lewy); //metoda stawiająca pionek o podanym kolorzena polu
    void usunPionek( IPlansza plansza); //metoda usuwająca pionek z pola
    IKamien podajKamien();
    void ustawKamien(IKamien kamien);
    void ustawPole();
    int podajX();
    int podajY();
}
