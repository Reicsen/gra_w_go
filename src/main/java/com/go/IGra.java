package com.go;

public interface IGra
{
    void koniecGry(); //metoda kończąca grę i informująca graczy o zwycięstwie/porażce
    void pominiecieTury(); //metoda zmieniająca aktywnego gracza w przypadku pominięcia tury
    void probaRuchu(); //metoda obsługująca wykonanie ruchu lub informująca gracza o jego niepoprawności
}
