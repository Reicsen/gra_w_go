package com.go.BazyDanych;

import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.go.GUI.GuiPlanszaOdtworzenia;

public class TworzeniePlanszyOdtworzenia implements IBazyDanychAdapter
{
    protected List<Integer> ruchy;
    protected List<Integer> gracze;

    public TworzeniePlanszyOdtworzenia(List<Integer> listaRuchow, List<Integer> listaGraczy)
    {
        this.ruchy=listaRuchow;
        this.gracze=listaGraczy;
    }

    public void obsluz()
    {
        GridPane gridPane1 = new GuiPlanszaOdtworzenia(ruchy,gracze);
        GridPane gridPane2 = new GuiPlanszaOdtworzenia(ruchy,gracze);
        Scene scenaGry = new Scene(gridPane1,900,1000);
        Stage bazaGry = new Stage();
        bazaGry.setTitle("Gra w go: replay");
        bazaGry.setScene(scenaGry);
        bazaGry.show();
    }
}
