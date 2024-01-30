package com.go.BazyDanych;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.go.GUI.GuiPlanszaOdtworzenia;
import com.go.Gra.Klienci.BrakSerwera;

public class TworzeniePlanszyOdtworzenia implements IStworzGui
{
    protected List<Integer> ruchy;
    protected List<Integer> gracze;

    public TworzeniePlanszyOdtworzenia(List<Integer> listaRuchow, List<Integer> listaGraczy)
    {
        this.ruchy=listaRuchow;
        this.gracze=listaGraczy;
    }

    public void stworzOkno()
    {
        try
        {
            GridPane gridPane1 = new GuiPlanszaOdtworzenia(ruchy,gracze);
            GridPane gridPane2 = new GuiPlanszaOdtworzenia(ruchy,gracze);
            Scene scenaGry = new Scene(gridPane1,900,1000);
            Stage bazaGry = new Stage();
            bazaGry.setTitle("Gra w go: replay");
            bazaGry.setScene(scenaGry);
            bazaGry.show();
        }
        catch(BrakSerwera e)
        {
            System.out.println("Brak serwera!");
        }
    }
}
