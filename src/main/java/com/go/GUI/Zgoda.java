package com.go.GUI;

import java.util.List;

import com.go.Gracz;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Zgoda extends GridPane{
    
    Zgoda(List<Integer> lista, Gracz gracz, Stage stage, GuiPlansza plansza){

        super();
        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");

        //Tworzymy label na którym będą wyświetlać się komunikaty
        Label lbl = new Label();
        lbl.setPrefWidth(920);
        lbl.setPrefHeight(50);
        lbl.setStyle("-fx-background-color: white;");
        lbl.setFont(Font.font(20));
        lbl.setText("Na fioletowo zaznaczony jest twój teren, a na niebiesko przeciwnika\nKliknij edytuj jesli chcesz samodzielnie ustalić terytorium");
        setColumnSpan(lbl, 19); // Rozciąganie przez 16 kolumn
        add(lbl, 0, 0);

        //tworzymy plansze
        FactoryButton factory = new StworzButton();
        for (int row = 1; row < 20; row++) {
            for (int col = 0; col < 19; col++) {

                Button button = factory.przycisk(lista.get((row-1) * 19 + col));

                add(button, col, row);

                setHalignment(button, HPos.CENTER);
            }
        }

        //tworzymy przycisk Zgoda
        Button b1 = new Button("Zgoda");
        b1.setPrefWidth(440);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 5); 
        add(b1, 0, 20);
        b1.setOnAction(event -> {

            //TODO - gracz.zgoda

            stage.close();
        });

        //tworzymy przecisk wróć do gry
        Button b2 = new Button("Wróć do gry");
        b2.setPrefWidth(440);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: aquamarine");
        b2.setFont(Font.font(20));
        setColumnSpan(b2, 5); 
        add(b2, 7, 20);
        b2.setOnAction(event -> {
            
            //TODO - gracz.powrótDoGry

            stage.close();
        });

        //tworzymy przecisk edytuj 
        Button b3 = new Button("Edytuj");
        b3.setPrefWidth(440);
        b3.setPrefHeight(50);
        b3.setStyle("-fx-background-color: aquamarine");
        b3.setFont(Font.font(20));
        setColumnSpan(b3, 5); 
        add(b3, 14, 20);
        b3.setOnAction(event -> {

            plansza.zaznaczTeren(gracz.podajListe());
            stage.close();
        });
    }
}
