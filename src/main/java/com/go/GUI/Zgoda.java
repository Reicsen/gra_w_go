package com.go.GUI;

import java.util.List;

import com.go.Gracz;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Zgoda extends GridPane{
    
    Zgoda(List<String> lista, Gracz gracz, Stage stage, GuiPlansza plansza){

        super();
        System.out.println("Weszlo");
        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");

        //tworzymy plansze
        FactoryButton factory = new StworzButton();
        for (int row = 1; row < 20; row++) {
            for (int col = 0; col < 19; col++) {

                Button button = factory.przycisk(lista.get((row-1) * 19 + col));

                add(button, col, row);

                setHalignment(button, HPos.CENTER);
            }
        }
        System.out.println("plansza");

        //tworzymy przycisk Zgoda
        Button b1 = new Button("Zgoda");
        b1.setPrefWidth(450);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 5); 
        add(b1, 0, 20);
        b1.setOnAction(event -> {

            //TODO - gracz.zgoda

            stage.close();
        });

        System.out.println("b1");
        //tworzymy przecisk wróć do gry
        Button b2 = new Button("Wróć do gry");
        b2.setPrefWidth(450);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: aquamarine");
        b2.setFont(Font.font(20));
        setColumnSpan(b2, 5); 
        add(b2, 7, 20);
        b2.setOnAction(event -> {
            
            //TODO - gracz.powrótDoGry

            stage.close();
        });

        System.out.println("b2");
        //tworzymy przecisk edytuj 
        Button b3 = new Button("Edytuj");
        b3.setPrefWidth(450);
        b3.setPrefHeight(50);
        b3.setStyle("-fx-background-color: aquamarine");
        b3.setFont(Font.font(20));
        setColumnSpan(b3, 5); 
        add(b3, 14, 20);
        b3.setOnAction(event -> {

            plansza.zaznaczTeren(gracz.podajListe());
            stage.close();
        });
        System.out.println("b3");
    }
}
