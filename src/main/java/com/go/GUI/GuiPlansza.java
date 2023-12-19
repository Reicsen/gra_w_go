package com.go.GUI;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GuiPlansza extends GridPane {
    GuiPlansza(){
        super();
        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");

        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                Button button = new PrzyciskPionek();

                add(button, col, row);

                setHalignment(button, HPos.CENTER);
            }
        }

        Button b1 = new Button("Poddaj się");
        b1.setPrefWidth(940);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 16); // Rozciąganie przez 16 kolumn
        add(b1, 0, 16);

        Button b2 = new Button("Pomiń ruch");
        b2.setPrefWidth(940);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: turquoise");
        b2.setFont(Font.font(20));
        setColumnSpan(b2, 16); // Rozciąganie przez 16 kolumn
        add(b2, 0, 17);
    }
}
