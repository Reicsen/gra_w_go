package com.go;

public interface Klient
{
    /*
     * Funkcja pisze do serweru czy można postawić pionek w tym miejscu
     */
    void wykonajRuch(int x, int y);

    /*
     * Funkcja powoduje pominięcie ruchu przez jednego z graczy 
     */
    void pominRuch();

    /*
     * Funkcja powoduje poddanie się jednego z graczy
     */
    void poddajSie();
    /*
     * Funkcja ustawia prawidłowy kolor dla danego gracza i jego przeciwnika
     * Biały dla gracza jeśli doszedł do gry jako drugi
     * Czarny jeśli był pierwszy
     */
    void ustawKolor(int nrGracza);

    /*
     * Funkcja zmienia aktywność gracza na przeciwną
     */
    void zmienAktywnosc();
}
