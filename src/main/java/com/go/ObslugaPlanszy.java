package com.go;

import com.go.GUI.GuiPlansza;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public interface ObslugaPlanszy
{
    /*
     * Funkcja zwraca true jeśli udało się dodać pionek do Planszy 
     * Funkcja zwaraca false jeśli nie udało się dodać pionka do planszy
     */
    void dodaniePionka(int nrpola, Color kolor);

    void usunieciePionka(int nrpola);

    /*
     * Funkcja wypisuje komunikat na planszy 
     */
    void wypiszKomunikatNaPlanszy(String komunikat);

    void wykonajRuch(int x, int y);
}
