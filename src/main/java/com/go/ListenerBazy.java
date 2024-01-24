package com.go;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListenerBazy implements IListenerBazy
{
    private List<Integer> gracze;
    private List<Integer> pola;

    public ListenerBazy()
    {
        this.gracze=new ArrayList<Integer>();
        this.pola=new ArrayList<Integer>();
    }

    public void dodaj(int gracz, int pole)
    {
        this.gracze.add(gracz);
        this.pola.add(pole);
        if (pole==-1)
        {
            koniec(gracz); //gracz to ten, który się poddał
        }
    }

    public void koniec(int gracz)
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            String temp;
            ResultSet wyniki;

            if(gracz==1)
            {
                temp="EXECUTE dodaj_gre USING 'bialy';";
            }
            else
            {
                temp="EXECUTE dodaj_gre USING 'czarny';";
            }
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
        }
    }
}
