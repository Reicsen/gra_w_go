package com.go.GUI;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class WyborGry extends GridPane{
    public WyborGry(Stage stage, IBazyDanychAdapter baza){

        super();

        //label z napisem Id gry
        Label kol1 = new Label("Id gry");
        kol1.setFont(Font.font("Callibri",FontWeight.BOLD,20));
        kol1.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null)));
        kol1.setWrapText(true);
        kol1.setTextAlignment(TextAlignment.CENTER);
        kol1.setMinWidth(300);
        kol1.setMaxWidth(300);
        kol1.setMinHeight(30);
        kol1.setMaxHeight(30);

        //label z napisem zwycięzca
        Label kol2 = new Label("Zwycięzca");
        kol2.setFont(Font.font("Callibri",FontWeight.BOLD,20));
        kol2.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,null,null)));
        kol2.setWrapText(true);
        kol2.setTextAlignment(TextAlignment.CENTER);
        kol2.setMinWidth(300);
        kol2.setMaxWidth(300);
        kol2.setMinHeight(30);
        kol2.setMaxHeight(30);

        //dodanie labeli
        addRow(0, kol1,kol2);

        //dodanie gier do wyboru

        List<String> lista = baza.listaGier();
        for(int i = 0; i < (lista.size()/2); i++){

            Button temp1 = new IDButton(lista.get(i*2),lista.get(i*2), stage);

            temp1.setFont(Font.font("Callibri",FontWeight.BOLD,20));
            temp1.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null)));
            temp1.setWrapText(true);
            temp1.setTextAlignment(TextAlignment.CENTER);
            temp1.setMinWidth(300);
            temp1.setMaxWidth(300);
            temp1.setMinHeight(30);
            temp1.setMaxHeight(30);

            Button temp2 = new IDButton(lista.get(i*2), lista.get(i*2+1), stage);

            temp2.setFont(Font.font("Callibri",FontWeight.BOLD,20));
            temp2.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,null,null)));
            temp2.setWrapText(true);
            temp2.setTextAlignment(TextAlignment.CENTER);
            temp2.setMinWidth(300);
            temp2.setMaxWidth(300);
            temp2.setMinHeight(30);
            temp2.setMaxHeight(30);
            addRow(i+1, temp1,temp2);
        }
    }
}
