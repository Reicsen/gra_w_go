package com.go.BazyDanych;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.random.MersenneTwister;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestyKonstruktorow
{
    @Test
    public void testKonstruktoraAdapterSQL()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wyniki = kwerenda.executeQuery("EXECUTE lista_gier;");
            IBazyDanychAdapter test = new AdapterSQL(wyniki);
            ResultSet testWynik = ((AdapterSQL) test).wyniki;
            boolean kontrolka=true;
            while (wyniki.next() && testWynik.next())
            {
                if ((!wyniki.getString("wygrany").equals(testWynik.getString("wygrany"))) || wyniki.getInt("id")!=testWynik.getInt("id"))
                {
                    kontrolka=false;
                }
            }
            if (wyniki.next() || testWynik.next())
            {
                kontrolka=false;
            }

            assertTrue(kontrolka);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testKonstruktoraOdczytRuchow()
    {
        int temp;
        MersenneTwister generator = new MersenneTwister();
        for (int i=0; i<500; i++)
        {
            temp=generator.nextInt(1000000);
            IBazyDanychAdapter test = new OdczytRuchow(temp);
            assertEquals(temp,((OdczytRuchow) test).nrGry);
        }
    }

    @Test
    public void testKonstruktoraAdapterSQL2()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wyniki = kwerenda.executeQuery("EXECUTE wez_id_gry;");
            wyniki.first();
            int nr = wyniki.getInt(1);
            wyniki = kwerenda.executeQuery("EXECUTE lista_ruchow USING "+nr+";");
            IBazyDanychAdapter test = new AdapterSQL2(wyniki);
            ResultSet testWynik = ((AdapterSQL2) test).wyniki;
            boolean kontrolka=true;
            while (wyniki.next() && testWynik.next())
            {
                if (wyniki.getInt("gracz")!=testWynik.getInt("gracz") || wyniki.getInt("pole")!=testWynik.getInt("pole"))
                {
                    kontrolka=false;
                }
            }
            if (wyniki.next() || testWynik.next())
            {
                kontrolka=false;
            }

            assertTrue(kontrolka);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testKonstruktoraTworzeniePlanszyOdtworzenia()
    {
        List<Integer> lRuchow = new ArrayList<Integer>();
        List<Integer> lGraczy = new ArrayList<Integer>();
        MersenneTwister generator = new MersenneTwister();
        for (int i=0; i<500; i++)
        {
            lGraczy.add(generator.nextInt(1000000));
            lRuchow.add(generator.nextInt(1000000));            
        }
        IBazyDanychAdapter test = new TworzeniePlanszyOdtworzenia(lRuchow, lGraczy);
        List<Integer> testR = ((TworzeniePlanszyOdtworzenia) test).ruchy;
        List<Integer> testG = ((TworzeniePlanszyOdtworzenia) test).gracze;
        
        boolean kontrolka=true;

        if (lRuchow.size()!=testR.size() || lGraczy.size()!=testG.size())
        {
            kontrolka=false;
        }
        else
        {
            for(int i=0; i<lGraczy.size(); i++)
            {
                if (lGraczy.get(i)!=testG.get(i) || lRuchow.get(i)!=testR.get(i))
                {
                    kontrolka=false;
                    break;
                }
            }
        }
        assertTrue(kontrolka);
    }
}
