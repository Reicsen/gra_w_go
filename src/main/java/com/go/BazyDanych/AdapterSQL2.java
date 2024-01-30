package com.go.BazyDanych;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.go.GUI.OkienkoBledu;

public class AdapterSQL2 implements IBazyDanychAdapter
{
    protected ResultSet wyniki;

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
                
            IStworzGui stworzPlansze = new TworzeniePlanszyOdtworzenia(ruchy, gracze);
            stworzPlansze.stworzOkno();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            OkienkoBledu.wyswietlBlad("Wystąpił nieprzewidziany błąd");
        }
    }
}
