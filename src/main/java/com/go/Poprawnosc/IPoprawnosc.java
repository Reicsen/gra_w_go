package com.go.Poprawnosc;

import com.go.Gra.IPlansza;

public interface IPoprawnosc {
    boolean sprawdzPoprawnosc(IPlansza plansza, int x, int y, String kolor);
}
