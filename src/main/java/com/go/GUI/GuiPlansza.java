package com.go.GUI;

import java.util.ArrayList;
import java.util.List;
import com.go.ObslugaPlanszy;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class GuiPlansza extends GridPane 
{
    private ObslugaPlanszy gracz;
    public Label lbl;
    public List<Button> pionki= new ArrayList<>();

    GuiPlansza(ObslugaPlanszy ob)
    {
        super();
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
        setColumnSpan(lbl, 16); // Rozciąganie przez 16 kolumn
        add(lbl, 0, 0);

        for (int row = 1; row < 17; row++) {
            for (int col = 0; col < 16; col++) {
                Button button = new PrzyciskPionek();
                button.setStyle("-fx-background-color: white;");

                add(button, col, row);

                setHalignment(button, HPos.CENTER);

                pionki.add(button);
                button.setDisable(true);
            }
        }

        Button b1 = new Button("Poddaj się");
        b1.setPrefWidth(940);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 16); // Rozciąganie przez 16 kolumn
        add(b1, 0, 17);

        Button b2 = new Button("Pomiń ruch");
        b2.setPrefWidth(940);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: turquoise");
        b2.setFont(Font.font(20));
        setColumnSpan(b2, 16); // Rozciąganie przez 16 kolumn
        add(b2, 0, 18);
        gracz = ob;
        gracz.setGuiPlansza(this);
    }
    public void rozpoczecieGry(){
        
        lbl.setText("Gra się rozpoczęła!");
        for (Button b : pionki) {
            b.setDisable(false);
        }
    }
}
