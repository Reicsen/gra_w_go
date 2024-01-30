package com.go.GUI;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.go.Gra.Klienci.BrakSerwera;

public class TestyBleduPolaczenia
{
    @Test
    public void testBleduPolaczeniaGracza()
    {
        assertThrows(BrakSerwera.class,()->{new GuiPlansza();});
    }
    @Test
    public void testBleduPolaczeniaOdtwarzania()
    {
        assertThrows(BrakSerwera.class,()->{new GuiPlanszaOdtworzenia(new ArrayList<Integer>(), new ArrayList<Integer>());});
    }
}
