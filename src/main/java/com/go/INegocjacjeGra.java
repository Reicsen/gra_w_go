package com.go;

public interface INegocjacjeGra
{
    void dwaRazyPominietoTure(int nrGracza); //numer gracza, który ma zacząć negocjacje
    void zerwanoNegocjacje(int nrGracza); //numer gracza, który zerwał negocjacje
    void przekazanieTerenow();
    void zaakceptowanoTerytoria();
}
