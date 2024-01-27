package com.go.GUI;

import java.sql.*;

import com.go.OkienkoBledu;

public class PobieranieDanychZBazy implements IBazyDanychAdapter
{
    public void obsluz()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wyniki = kwerenda.executeQuery("EXECUTE lista_gier;");
            ResultSet ile = kwerenda.executeQuery("EXECUTE ilosc_gier");
            ile.first();
            int ilosc = ile.getInt(1);

            if(ilosc==0)
            {
                OkienkoBledu.wyswietlBlad("ERROR#404: brak gier w bazie");
            }

            else
            {
                IBazyDanychAdapter adapter = new AdapterSQL(wyniki);
                adapter.obsluz();
            }
            wyniki.close();
                ile.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            OkienkoBledu.wyswietlBlad("Nie udało się połączyć z bazą");
        }
    }
}
