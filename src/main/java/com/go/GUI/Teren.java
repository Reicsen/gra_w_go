package com.go.GUI;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.List;

public class Teren extends GridPane{

    public Label lbl;
    public List <Integer> twojTeren;
    public List <Integer> przeciwnikaTeren;

    Teren (List<String> lista){

        super();
        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");
    
        //Tworzymy label na którym będą wyświetlać się komunikaty
        lbl = new Label();
        lbl.setPrefWidth(920);
        lbl.setPrefHeight(50);
        lbl.setStyle("-fx-background-color: white;");
        lbl.setFont(Font.font(20));
        lbl.setText("Zaznacz swoj teren");
        setColumnSpan(lbl, 19); // Rozciąganie przez 16 kolumn
        add(lbl, 0, 0);

        //tworzymy plansze
        for (int row = 1; row < 20; row++) {
            for (int col = 0; col < 19; col++) {

                Button button = new ZaznaczTeren(lista.get( (row-1) * 19 + col ));
                button.setStyle("-fx-background-color: white;");

                add(button, col, row);

                setHalignment(button, HPos.CENTER);
            }
        }

        //tworzymy przycisk zaznacz
        Button b1 = new Button("Skończ zaznaczanie");
        b1.setPrefWidth(450);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 10); 
        add(b1, 0, 20);
        b1.setOnAction(event -> {
            //TODO
        });
        
    }
}
