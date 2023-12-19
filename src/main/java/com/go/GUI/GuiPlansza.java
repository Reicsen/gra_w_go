package com.go.GUI;

import java.util.ArrayList;
import java.util.List;

import com.go.Gracz;
import com.go.Klient;

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
    private Klient gracz;
    public Label lbl;
    public List<PrzyciskPionek> pionki= new ArrayList<>();
    public Color kolor;

    public GuiPlansza()
    {
        //Tworzymy nowy GridPane
        super();
        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");

        //Tworzymy nowego gracza
        gracz = new Gracz(this);
    
        //Tworzymy label na którym będą wyświetlać się komunikaty
        lbl = new Label();
        lbl.setPrefWidth(965);
        lbl.setPrefHeight(50);
        lbl.setStyle("-fx-background-color: white;");
        lbl.setFont(Font.font(20));
        lbl.setText(" Czekanie na drugiego gracza");
        setColumnSpan(lbl, 19); // Rozciąganie przez 16 kolumn
        add(lbl, 0, 0);

        //Tworzymy przyciski, które po wciśnięciu dodają pionek w danym miejscu
        //Przycisków jest tyle ile przecięć linji w planszy
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

        //Dodajemy przycisk który po naciśnięciu powoduje poddanie się gracza
        Button b1 = new Button("Poddaj się");
        b1.setPrefWidth(500);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 10); 
        add(b1, 0, 20);
        b1.setOnAction(event -> {
            gracz.poddajSie();
        });

        //Dodajemy przycisk który po naciśnięciu powoduje pominięcie ruchu przez gracza
        Button b2 = new Button("Pomiń ruch");
        b2.setPrefWidth(480);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: turquoise");
        b2.setFont(Font.font(20));
        setColumnSpan(b2, 9);
        add(b2, 10, 20);
        b2.setOnAction(event -> {
            gracz.pominRuch();
        });
    }
    //Funkcja, która wywoływana jest na początku rozgrywki
    //Uruchamia ona przyciski dostawiające pionki
    public void rozpoczecieGry(){
        Platform.runLater(() -> {
        for (Button b : pionki) {
            b.setDisable(false);
        }
    });
    }
}
