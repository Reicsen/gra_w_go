package com.go.BazyDanych;

import org.apache.commons.math3.random.MersenneTwister;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestyIDButton
{
    @Test
    public void testKonstruktoraStringInt()
    {
        MersenneTwister generator = new MersenneTwister();
        String tytul = "b oeywgcofiueevanmurcxan";
        int id = generator.nextInt(1000000);
        
        IDButton test = new IDButton(id, tytul);

        assertEquals(id,test.zwrocId());
        assertEquals(tytul,test.getText());
    }

    @Test
    public void testKonstruktoraInt()
    {
        MersenneTwister generator = new MersenneTwister();
        for (int i=0; i<100; i++)
        {
            int id = generator.nextInt(1000000);
            IDButton test = new IDButton(id);
            assertEquals(id,test.zwrocId());
            assertEquals(Integer.toString(id),test.getText());
        }
    }
}
