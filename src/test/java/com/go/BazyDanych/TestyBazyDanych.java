package com.go.BazyDanych;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestyBazyDanych
{
    @Test
    public void testBazyDanych1()
    {
        List<Integer> testRuchow = new ArrayList<Integer>();
        List<Integer> testGraczy = new ArrayList<Integer>();
        IListenerBazy testowyListener = new ListenerBazy();
        for(int i=0; i<100; i++)
        {
            testRuchow.add(i);
            testGraczy.add(i%2+1);
            testowyListener.dodaj(i%2+1,i);
        }
        testRuchow.add(-1);
        testGraczy.add(1);
        testowyListener.dodaj(1,-1);

        boolean kontrolka=true;
        List<Integer> listaPol = testowyListener.wezListeRuchow();
        List<Integer> listaGraczy = testowyListener.wezListeGraczy();

        if(testGraczy.size()!=listaGraczy.size() || testRuchow.size()!=listaPol.size())
        {
            kontrolka=false;
        }
        else
        {
            for(int i=0; i<testRuchow.size(); i++)
            {
                if (testRuchow.get(i)!=listaPol.get(i) || testGraczy.get(i)!=listaGraczy.get(i))
                {
                    kontrolka = false;
                    break;
                }
            }
        }

        assertTrue(kontrolka);
    }

    @Test
    public void testBazyDanych2()
    {
        List<Integer> testRuchow = new ArrayList<Integer>();
        List<Integer> testGraczy = new ArrayList<Integer>();
        //String testZwyciezcy;
        for(int i=0; i<100; i++)
        {
            testRuchow.add(i);
            testGraczy.add(i%2+1);
        }
        testRuchow.add(-1);
        testGraczy.add(1);
        IBazyDanychAdapter test = new ZapisDoBazy(testGraczy, testRuchow, "");

        boolean kontrolka=true;
        List<Integer> listaPol = ((ZapisDoBazy) test).pola;
        List<Integer> listaGraczy = ((ZapisDoBazy) test).gracze;

        if(testGraczy.size()!=listaGraczy.size() || testRuchow.size()!=listaPol.size())
        {
            kontrolka=false;
        }
        else
        {
            for(int i=0; i<testRuchow.size(); i++)
            {
                if (testRuchow.get(i)!=listaPol.get(i) || testGraczy.get(i)!=listaGraczy.get(i))
                {
                    kontrolka = false;
                    break;
                }
            }
        }

        assertTrue(kontrolka);
    }

    @Test
    public void testBazyDanych3()
    {
        List<Integer> testRuchow = new ArrayList<Integer>();
        List<Integer> testGraczy = new ArrayList<Integer>();
        //String testZwyciezcy;
        for(int i=0; i<5; i++)
        {
            testRuchow.add(i);
            testGraczy.add(i%2+1);
        }
        testRuchow.add(-1);
        testGraczy.add(1);
        IBazyDanychAdapter test = new ZapisDoBazy(testGraczy, testRuchow, "czarny");

        assertTrue("czarny".equals(((ZapisDoBazy) test).zwyciezca));
    }

    @Test
    public void testBazyDanych4()
    {
        List<Integer> testRuchow = new ArrayList<Integer>();
        List<Integer> testGraczy = new ArrayList<Integer>();
        int maksIdGry;
        for(int i=0; i<100; i++)
        {
            testRuchow.add(i);
            testGraczy.add(i%2+1);
        }
        testRuchow.add(-1);
        testGraczy.add(1);
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wynik = kwerenda.executeQuery("EXECUTE wez_id_gry;");
            wynik.first();
            maksIdGry=wynik.getInt(1);
            List<Integer> odczytaneRuchy = new ArrayList<Integer>();
            List<Integer> odczytaniGracze = new ArrayList<Integer>();
            wynik=kwerenda.executeQuery("EXECUTE lista_ruchow USING "+maksIdGry+";");
            while (wynik.next())
            {
                odczytaneRuchy.add(wynik.getInt("pole"));
                odczytaniGracze.add(wynik.getInt("gracz"));
            }
            boolean kontrolka=true;
            for(int i=0; i<testRuchow.size(); i++)
            {
                if (testRuchow.get(i)!=odczytaneRuchy.get(i) || testGraczy.get(i)!=odczytaniGracze.get(i))
                {
                    kontrolka = false;
                    break;
                }
            }
            assertTrue(kontrolka);

            kwerenda.executeQuery("EXECUTE delete1 USING "+maksIdGry+";");
            kwerenda.executeQuery("ALTER TABLE Gry AUTO_INCREMENT="+maksIdGry+";");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testBazyDanych5()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wynik = kwerenda.executeQuery("EXECUTE wez_id_gry;");
            wynik.first();
            int maksIdGry=wynik.getInt(1);
            ResultSet wyniki=kwerenda.executeQuery("EXECUTE lista_ruchow USING "+maksIdGry+";");
            IBazyDanychAdapter test = new OdczytRuchow(maksIdGry);
            test.obsluz();
            ResultSet testWynik = ((OdczytRuchow) test).wyniki;

            while (wyniki.next() && testWynik.next())
            {
                assertEquals(wyniki.getInt(1),testWynik.getInt(1));
                assertEquals(wyniki.getInt(2),testWynik.getInt(2));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testBazyDanych6()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wynik = kwerenda.executeQuery("EXECUTE wez_id_gry;");
            wynik.first();
            int maksIdGry=wynik.getInt(1);
            ResultSet wyniki=kwerenda.executeQuery("EXECUTE lista_ruchow USING "+maksIdGry+";");
            IBazyDanychAdapter test = new AdapterSQL2(wyniki);
            test.obsluz();
            List<Integer> testGraczy = ((AdapterSQL2) test).gracze;
            List<Integer> testRuchow = ((AdapterSQL2) test).ruchy;

            wyniki=kwerenda.executeQuery("EXECUTE lista_ruchow USING "+maksIdGry+";");
            List<Integer> lGraczy = new ArrayList<Integer>();
            List<Integer> lRuchow = new ArrayList<Integer>();
            while (wyniki.next())
            {
                lGraczy.add(wyniki.getInt("gracz"));
                lRuchow.add(wyniki.getInt("pole"));
            }

            assertEquals(lGraczy.size(),testGraczy.size());
            assertEquals(lRuchow.size(),testRuchow.size());
            for (int i=0; i<lRuchow.size(); i++)
            {
                assertEquals(lGraczy.get(i),testGraczy.get(i));
                assertEquals(lRuchow.get(i),testRuchow.get(i));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
