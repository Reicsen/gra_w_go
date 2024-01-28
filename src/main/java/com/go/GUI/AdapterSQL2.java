package com.go.GUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.go.OkienkoBledu;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdapterSQL2 implements IBazyDanychAdapter
{
    private ResultSet wyniki;

    public AdapterSQL2(ResultSet wynik)
    {
        this.wyniki = wynik;
    }

    public void obsluz()
    {
        try
        {
            List<Integer> ruchy = new ArrayList<Integer>();
            List<Integer> gracze = new ArrayList<Integer>();

            while(wyniki.next())
            {
                int ruch = wyniki.getInt("pole");
                int gracz = wyniki.getInt("gracz");

                ruchy.add(ruch);
                gracze.add(gracz);
            }
                
            IBazyDanychAdapter stworzPlansze = new TworzeniePlanszyOdtworzenia(ruchy, gracze);
            stworzPlansze.obsluz();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            OkienkoBledu.wyswietlBlad("Wystąpił nieprzewidziany błąd");
        }
    }
}
