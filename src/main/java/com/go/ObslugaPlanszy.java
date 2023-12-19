package com.go;

import javafx.scene.paint.Color;

public interface ObslugaPlanszy
{
    boolean rozpoczniGre();
    boolean dodaniePionka(int nrpola, Color kolor);
    void usunieciePionka(int nrpola);
    String wypiszKomunikatNaPlanszy(String komunikat);
}
