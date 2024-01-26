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
            
            IBazyDanychAdapter wyborGry = new OknoWyboruGry(ilosc, wyniki);
            wyborGry.obsluz();
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
