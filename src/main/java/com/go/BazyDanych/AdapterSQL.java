package com.go.BazyDanych;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.go.GUI.OkienkoBledu;


public class AdapterSQL implements IBazyDanychAdapter
{
    protected ResultSet wyniki;

    public AdapterSQL(ResultSet wynik)
    {
        this.wyniki = wynik;
    }

    public void obsluz()
    {
        try
        {
            List<Integer> id = new ArrayList<Integer>();
            List<String> zwyciezcy = new ArrayList<String>();

            while(wyniki.next())
            {
                int idGry = wyniki.getInt("id");
                String wygrany = wyniki.getString("wygrany");

                id.add(idGry);
                zwyciezcy.add(wygrany);
            }
                
            IStworzGui wyborGry = new OknoWyboruGry(id, zwyciezcy);
            wyborGry.stworzOkno();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            OkienkoBledu.wyswietlBlad("Wystąpił nieprzewidziany błąd");
        }
    }
}
