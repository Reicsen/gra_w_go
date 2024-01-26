package com.go.GUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BazyDanychAdapter implements IBazyDanychAdapter{
    Connection polaczenie;
    Statement kwerenda;

    public boolean start(){
        try 
        {
            polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            kwerenda = (Statement) polaczenie.createStatement();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> listaGier(){
        List<String> lista= new ArrayList<>();

        try 
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wyniki = kwerenda.executeQuery("EXECUTE lista_gier;");
            int id = wyniki.getInt("id");
            String wygrany = wyniki.getString("wygrany");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return lista;
    }
}
