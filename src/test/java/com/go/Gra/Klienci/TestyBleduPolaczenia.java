package com.go.Gra.Klienci;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import com.go.GUI.GuiPlansza;
import com.go.GUI.GuiPlanszaOdtworzenia;

public class TestyBleduPolaczenia
{
    @Test(expected = BrakSerwera.class)
    public void testBleduPolaczeniaGracza()
    {
        new Gracz(new GuiPlansza());
    }

    @Test(expected = BrakSerwera.class)
    public void testBleduPolaczeniaBota()
    {
        new Bot();
    }
    
    @Test(expected = BrakSerwera.class)
    public void testBleduPolaczeniaOdtwarzania()
    {
        new Odtworzenie(new GuiPlanszaOdtworzenia(new ArrayList<Integer>(), new ArrayList<Integer>()), new ArrayList<Integer>(), new ArrayList<Integer>());
    }
}
