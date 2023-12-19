package com.go;

import javafx.scene.paint.Color;

public interface ObslugaPlanszy
{
    /*
     * Funkcja zwraca true jeśli do serwera dołączyło dwóch graczy
     * Funkcja zwraca false jeśli jeszcze czekamy na kolejnego gracza
     */
    boolean rozpoczniGre();

    /*
     * Funkcja zwraca true jeśli udało się dodać pionek do Planszy 
     * Funkcja zwaraca false jeśli nie udało się dodać pionka do planszy
     */
    boolean dodaniePionka(int nrpola, Color kolor);
    
    void usunieciePionka(int nrpola);

    /*
     * Funkcja zwraca String którego chcemy wypisać na planszy jako komunikat
     */
    String wypiszKomunikatNaPlanszy(String komunikat);
}
