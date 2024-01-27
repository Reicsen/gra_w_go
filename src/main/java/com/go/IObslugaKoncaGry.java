package com.go;

import java.util.List;

public interface IObslugaKoncaGry
{
    void proceduraKoncowa(List<Integer> czarneTerytorium, List<Integer> bialeTerytorium, List<Integer> czarnePionyNaBialym, List<Integer> bialePionyNaCzarnym);
    int iloscJencow(String kolor);
    void usunPionyZeZlychTerytoriow(List<Integer> czarnePionyNaBialym, List<Integer> bialePionyNaCzarnym);
    void wstawJencow(List<Integer> czarneTerytorium, List<Integer> bialeTerytorium, int czarniJency, int bialiJency);
}
