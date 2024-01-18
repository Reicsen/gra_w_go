package com.go;

import java.util.ArrayList;

/**
 * ITerytorium
 */
public interface ITerytorium {

    /*
     * Na podstawie planszy oblicza się terytorium dla gracza o pionach w podanym kolorze
     */
    int obliczTerytorium(IPlansza plansza, String kolor);

    /*
     * Na podstawie planszy określa się które piony znajdują się na terytorium gracza o pionach w podanym kolorze
     */
    ArrayList <Integer> PionyNaTerytoriumPrzeciwnika(IPlansza plansza, String kolor);
    
}
