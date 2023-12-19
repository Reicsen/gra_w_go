package com.go.GUI;

import java.util.ArrayList;
import java.util.List;

import com.go.Gracz;
import com.go.ObslugaPlanszy;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GuiPlansza extends GridPane 
{
    private ObslugaPlanszy gracz;
    public Label lbl;
    public List<PrzyciskPionek> pionki= new ArrayList<>();
    public Color kolor;

    GuiPlansza()
    {
        super();

        gracz = new Gracz(this);

        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");
    
        lbl = new Label();
        lbl.setPrefWidth(940);
        lbl.setPrefHeight(50);
        lbl.setStyle("-fx-background-color: white;");
        lbl.setFont(Font.font(20));
        lbl.setText(" Czekanie na drugiego gracza");
        setColumnSpan(lbl, 19); // Rozciąganie przez 16 kolumn
        add(lbl, 0, 0);

        for (int row = 1; row < 20; row++) {
            for (int col = 0; col < 19; col++) {
                PrzyciskPionek button = new PrzyciskPionek(gracz, row-1, col);
                button.setStyle("-fx-background-color: white;");

                add(button, col, row);

                setHalignment(button, HPos.CENTER);

                pionki.add(button);
                button.setDisable(true);
            }
        }

        Button b1 = new Button("Poddaj się");
        b1.setPrefWidth(480);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 10); 
        add(b1, 0, 20);

        Button b2 = new Button("Pomiń ruch");
        b2.setPrefWidth(480);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: turquoise");
        b2.setFont(Font.font(20));
        setColumnSpan(b2, 9);
        add(b2, 10, 20);
    }
    public void rozpoczecieGry(){
        Platform.runLater(() -> {
        
        //lbl.setText("Gra się rozpoczęła!");
        for (Button b : pionki) {
            b.setDisable(false);
        }
    });
    }
}
