package com.go;

import java.util.ArrayList;

public interface IUsuwaniePionkow {
    /*
     * zwraca liczbe pól gdzie kamień ma zero oddechów
     */
    int obliczanieJencow(IPlansza plansza);

    /*
     * zwraca liste miejsc z których zostaną usunięte pionki bo mają 0 oddechów
     */
    ArrayList<Integer> pionkiDoUsuniecia(IPlansza plansza);
    
    /*
     * usuwa pionki które mają 0 oddechów z planszy
     */
    void usunPionki(IPlansza plansza);
}
