package com.go.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class GUI {
    public GUI(Stage stage){
    
        stage.setTitle("Gra w go");

        GridPane root = new GridPane();

        //Dodanie przycisku, który po naciśnięciu uruchamia grę z drugim graczem
        Button b1 = new PrzyciskGraZGraczem(stage);
        b1.setPrefWidth(400);
        b1.setPrefHeight(200);
        b1.setStyle("-fx-background-color: turquoise");
        b1.setFont(Font.font(20));

        //Dodanie przycisku, który po naciśnięciu uruchamia grę z botem 
        Button b2 = new PrzyciskGraZBotem(stage);
        b2.setPrefWidth(400);
        b2.setPrefHeight(200);
        b2.setStyle("-fx-background-color: aquamarine");
        b2.setFont(Font.font(20));

        //Dodanie przycisku, który po naciśnięciu wybiera się gre do odtworzenie
        Button b3 = new PrzyciskOdtworzGre(stage);
        b3.setPrefWidth(400);
        b3.setPrefHeight(200);
        b3.setStyle("-fx-background-color: turquoise");
        b3.setFont(Font.font(20));

        root.add(b1, 0, 0);
        root.add(b2, 0, 1);
        root.add(b3, 0, 2);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show(); 
    }
    
}
