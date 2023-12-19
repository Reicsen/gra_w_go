package com.go;

import javafx.scene.paint.Color;

public interface ObslugaPlanszy
{
    void dodaniePionka(int nrpola, Color kolor);
    void usunieciePionka(int nrpola);
    void wypiszKomunikatNaPlanszy(String komunikat);
}
