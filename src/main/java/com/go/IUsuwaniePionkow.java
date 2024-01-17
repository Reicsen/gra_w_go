package com.go;

import java.util.ArrayList;

public interface IUsuwaniePionkow {
    /*
     * zwraca liczbe pól gdzie kamień ma zero oddechów
     */
    int obliczanieJencow(IPlansza plansza, String kolor);

    /*
     * zwraca liste miejsc z których zostaną usunięte pionki bo mają 0 oddechów
     */
    ArrayList<Integer> pionkiDoUsuniecia(IPlansza plansza, String kolor);

    /*
     * usuwa pionki które mają 0 oddechów z planszy
     */
    void usunPionki(IPlansza plansza, String kolor);
}
