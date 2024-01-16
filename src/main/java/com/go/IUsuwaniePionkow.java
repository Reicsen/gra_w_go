package com.go;

import java.util.ArrayList;

public interface IUsuwaniePionkow {
    int obliczanieJencow(IPlansza plansza);
    ArrayList<Integer> pionkiDoUsuniecia(IPlansza plansza);
    void usunPionki(IPlansza plansza);
}
