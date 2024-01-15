package com.go;

interface IPole {
    String podajPionek(); //metoda zwracająca, jaki pionek teraz jest na danym polu
    void dodajPionek(String kolor, IPole gora, IPole dol, IPole prawy, IPole lewy); //metoda stawiająca pionek o podanym kolorzena polu
    void usunPionek(); //metoda usuwająca pionek z pola
    IKamien podajKamien();
    void ustawKamien(IKamien kamien);
    void ustawPole();
}
