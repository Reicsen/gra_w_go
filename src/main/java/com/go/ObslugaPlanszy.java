package com.go;

import javafx.scene.paint.Color;

public interface ObslugaPlanszy
{
    /*
     *Funkcja dodaje na GuiPlanszy pionek w odpowiednim miejscu
     */
    void dodaniePionka(int nrpola, Color kolor);

    /*
     * Funkcja usuwa pionek z GuiPlanszy w odpowiednim miejscu
     */
    void usunieciePionka(int nrpola);

    /*
     * Funkcja wypisuje komunikat na planszy 
     */
    void wypiszKomunikatNaPlanszy(String komunikat);
}
