package com.go;

import java.util.List;

public interface IPlansza {
    void dodajPionek(String kolor, int x, int y); //metoda dodająca pionek o podanym kolorze na podane pole
    void usunPionek(int x, int y); //metoda usuwająca pionek z podanego pola
    boolean sprawdzPoprawnosc(String kolor, int x, int y); //meotda sprawdzająca poprawnoś ruchu na podane pole
    List<IPole> podajPola();
}
