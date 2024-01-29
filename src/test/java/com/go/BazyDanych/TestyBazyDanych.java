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

        for(int i=0; i<testRuchow.size(); i++)
        {
            if (testRuchow.get(i)!=listaPol.get(i) || testGraczy.get(i)!=listaGraczy.get(i))
            {
                kontrolka = false;
                break;
            }
        }

        assertTrue(kontrolka);
    }

    @Test
    public void testBazyDanych2()
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
}
