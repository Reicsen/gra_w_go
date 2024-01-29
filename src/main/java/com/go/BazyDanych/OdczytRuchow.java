package com.go.BazyDanych;

import java.sql.*;
import com.go.GUI.OkienkoBledu;

public class OdczytRuchow implements IBazyDanychAdapter
{
    protected int nrGry;

    public OdczytRuchow(int nr)
    {
        this.nrGry=nr;
    }

    public void obsluz()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wyniki = kwerenda.executeQuery("EXECUTE lista_ruchow USING "+this.nrGry+";");

            IBazyDanychAdapter adapterRuchow = new AdapterSQL2(wyniki);
            adapterRuchow.obsluz();
            wyniki.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            OkienkoBledu.wyswietlBlad("Nie udało się połączyć z bazą");
        }
    }
}
