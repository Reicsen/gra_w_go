package com.go.BazyDanych;

import java.sql.*;
import java.util.List;
import com.go.GUI.OkienkoBledu;

public class ZapisDoBazy implements IBazyDanychAdapter
{
    protected String zwyciezca;
    protected List<Integer> gracze;
    protected List<Integer> pola;
    
    public ZapisDoBazy(List<Integer> gr, List<Integer> p, String wygrany)
    {
        this.gracze=gr;
        this.pola=p;
        this.zwyciezca=wygrany;
    }

    public void obsluz()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            String temp;
            ResultSet wyniki;

            temp="EXECUTE dodaj_gre USING '"+this.zwyciezca+"';";

            kwerenda.executeQuery(temp);

            wyniki=kwerenda.executeQuery("EXECUTE wez_id_gry;");
            wyniki.first();
            int idGry=wyniki.getInt(1);

            for (int i=0; i<pola.size(); i++)
            {
                temp="EXECUTE dodaj_ruch USING "+idGry+", "+gracze.get(i)+", "+pola.get(i)+";";
                kwerenda.executeQuery(temp);
            }
            wyniki.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            OkienkoBledu.wyswietlBlad("Wystąpił błąd podczas zapisu do bazy danych");
        }
    }
}
