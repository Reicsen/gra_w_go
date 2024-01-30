package com.go.Gra.Klienci;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.go.GUI.GuiPlansza;
import com.go.GUI.GuiPlanszaOdtworzenia;

public class TestyBleduPolaczenia
{
    @Test
    public void testBleduPolaczeniaGracza()
    {
        assertThrows(BrakSerwera.class,()->{new Gracz(new GuiPlansza());});
    }

    @Test
    public void testBleduPolaczeniaBota()
    {
        assertThrows(BrakSerwera.class,()->{new Bot();});
    }
    
    @Test
    public void testBleduPolaczeniaOdtwarzania()
    {
        assertThrows(BrakSerwera.class,()->{new Odtworzenie(new GuiPlanszaOdtworzenia(new ArrayList<Integer>(), new ArrayList<Integer>()), new ArrayList<Integer>(), new ArrayList<Integer>());});
    }
}
