package com.go;

public interface Klient
{
    /*
     * Funkcja wysyła do serwera informację o próbie postawienia pionka na podanym polu
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
     * Funkcja zmienia aktywność gracza na przeciwną
     */
    void zmienAktywnosc();
}
