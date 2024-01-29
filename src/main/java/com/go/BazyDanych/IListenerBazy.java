package com.go.BazyDanych;

import java.util.List;

public interface IListenerBazy 
{
    void dodaj(int gracz, int pole);
    void koniec(int gracz);
    List<Integer> wezListeGraczy();
    List<Integer> wezListeRuchow();
}
