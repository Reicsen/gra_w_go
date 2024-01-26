package com.go.GUI;

import java.util.List;

public interface IBazyDanychAdapter {
    /*
     * Uruchamiamy połączenie z bazą
     */
    boolean start();
    
    /*
     * Zwracamy liste gier które można odtworzyć 
     * Dodajemy id i zwycięzcę
     * Na parzystych miejscach znajdują się id a na nie parzystych zwycięzcy
     */
    List<String> listaGier();
}
