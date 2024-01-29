package com.go.Gra.Klienci;

import java.util.List;
import javafx.scene.paint.Color;

public interface IListaNegocjacyjna
{
    void ustawListe();
    List<Integer> podajListe();
    void zmien(int nrpola, Color kolor);
    void usun(int nrpola);
}
