package com.go.GUI;


import java.util.List;
import com.go.OkienkoBledu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class OknoWyboruGry implements IBazyDanychAdapter
{
    private List<Integer> idGier;
    private List<String> zwyciezcyGier;
    private int iloscGier;
    
    public OknoWyboruGry(List<Integer> id, List<String> zwyciezcy)
    {
        this.idGier=id;
        this.zwyciezcyGier=zwyciezcy;
        this.iloscGier=idGier.size();
    }

    public void obsluz()
    {
        GridPane siatka = new GridPane();
        Stage baza = new Stage();

        Label kol1 = new Label("Id gry");
        kol1.setFont(Font.font("Callibri",FontWeight.BOLD,20));
        kol1.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null)));
        kol1.setWrapText(true);
        kol1.setTextAlignment(TextAlignment.CENTER);
        kol1.setMinWidth(300);
        kol1.setMaxWidth(300);
        kol1.setMinHeight(30);
        kol1.setMaxHeight(30);

        Label kol2 = new Label("Zwycięzca");
        kol2.setFont(Font.font("Callibri",FontWeight.BOLD,20));
        kol2.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,null,null)));
        kol2.setWrapText(true);
        kol2.setTextAlignment(TextAlignment.CENTER);
        kol2.setMinWidth(300);
        kol2.setMaxWidth(300);
        kol2.setMinHeight(30);
        kol2.setMaxHeight(30);

        siatka.addRow(0, kol1,kol2);

        EventHandler<ActionEvent> kliknieto = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle (ActionEvent event)
            {
                IDButton zrodlo = (IDButton) event.getSource();
                int id = zrodlo.zwrocId();
                GridPane gridPane1 = new GuiPlanszaOdtworzenia(id);
                GridPane gridPane2 = new GuiPlanszaOdtworzenia(id);
                baza.close();
            }
        };

        for(int i=0; i<iloscGier; i++)
        {
            int id = idGier.get(i);
            String wygrany = zwyciezcyGier.get(i);
            
            IDButton temp1 = new IDButton(id,Integer.toString(id));
            temp1.setFont(Font.font("Callibri",FontWeight.BOLD,20));
            temp1.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null)));
            temp1.setWrapText(true);
            temp1.setTextAlignment(TextAlignment.CENTER);
            temp1.setMinWidth(300);
            temp1.setMaxWidth(300);
            temp1.setMinHeight(30);
            temp1.setMaxHeight(30);

            IDButton temp2 = new IDButton(id,wygrany);
            temp2.setFont(Font.font("Callibri",FontWeight.BOLD,20));
            temp2.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,null,null)));
            temp2.setWrapText(true);
            temp2.setTextAlignment(TextAlignment.CENTER);
            temp2.setMinWidth(300);
            temp2.setMaxWidth(300);
            temp2.setMinHeight(30);
            temp2.setMaxHeight(30);

            temp1.setOnAction(kliknieto);
            temp2.setOnAction(kliknieto);

            siatka.addRow(i+1, temp1,temp2);
        }

        Scene scena = new Scene(siatka);
        baza.setTitle("");
        siatka.setMinWidth(600);
        siatka.setMaxWidth(600);
        siatka.setMinHeight((iloscGier+1)*30+40);
        siatka.setMaxHeight((iloscGier+1)*30+40);
        baza.setScene(scena);
        baza.setMinWidth(600);
        baza.setMaxWidth(600);
        baza.setMinHeight((iloscGier+1)*30+40);
        baza.setMaxHeight((iloscGier+1)*30+40);
        baza.show();
    }
}
