package com.go.Gra.Klienci;

import javafx.scene.paint.Color;

public interface ObslugaPlanszy
{
    /*
     * Funkcja ustawia prawidłowy kolor dla danego gracza i jego przeciwnika
     * Biały dla gracza jeśli doszedł do gry jako drugi
     * Czarny jeśli był pierwszy
     */
    void ustawKolor(int nrGracza);


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

    //Tworzy okienko z komunikatem błędu lub końca gry
    void okienko(String komunikat);
}
