package com.go;

import java.util.List;

/**
 * ITerytorium
 */
public interface ITerytorium {
    
    /*
     * Zwraca liste pionków które znajdują się na terytorium gracza o pionkach w kolorze kolor
     * Pobiera liste zawierającą terytorium gracza o pionkach w kolorze kolor
     */
    List <Integer> pionyDoUsuniecia(List <Integer> terytorium, IPlansza plansza, String kolor);
    
}
