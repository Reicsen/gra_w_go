package com.go.Gra.Klienci;

import java.util.List;

public interface IPrzesylanieTerytoriow
{
    void wyslijTeren(List <Integer> twojTeren, List <Integer> przeciwnikaTeren);
    void odbierzTeren();
    void wyslijJencow();
}
