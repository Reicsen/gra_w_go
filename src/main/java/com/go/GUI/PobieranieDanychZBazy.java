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
            int ilosc=1;

            IBazyDanychAdapter wyborGry = new OknoWyboruGry(ilosc, wyniki);
            wyborGry.obsluz();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            OkienkoBledu.wyswietlBlad("Nie udało się połączyć z bazą");
        }
    }
}
