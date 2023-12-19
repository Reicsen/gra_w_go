package com.go.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class GUI {
    public GUI(Stage stage){
    
        stage.setTitle("Gra w go");

        BorderPane root = new BorderPane();

        Button b1 = new PrzyciskGraZGraczem(stage);
        b1.setPrefWidth(400);
        b1.setPrefHeight(200);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));

        Button b2 = new PrzyciskGraZBotem();
        b2.setPrefWidth(400);
        b2.setPrefHeight(200);
        b2.setStyle("-fx-background-color: turquoise");
        b2.setFont(Font.font(20));


        root.setRight(b2);
        root.setLeft(b1);

        Scene scene = new Scene(root, 800, 200);
        stage.setScene(scene);
        stage.show(); 

    }
    
}
