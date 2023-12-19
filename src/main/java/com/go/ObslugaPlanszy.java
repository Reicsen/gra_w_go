package com.go;

import com.go.GUI.GuiPlansza;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public interface ObslugaPlanszy
{
    void setGuiPlansza(GuiPlansza plansza);
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
     * Funkcja wypisuje komunikat na planszy 
     */
    void wypiszKomunikatNaPlanszy(String komunikat);

    //void wlaczGUI(Label komunikaty);
}
