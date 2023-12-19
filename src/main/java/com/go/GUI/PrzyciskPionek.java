package com.go.GUI;

import com.go.ObslugaPlanszy;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class PrzyciskPionek extends Button{
    PrzyciskPionek(ObslugaPlanszy gracz, int nrpola, Color kolor){
        super();
        setMaxSize(30, 30);

        Line cross1 = new Line(15, 0, 15, 30);
        Line cross2 = new Line(0, 15, 30, 15);
        cross1.setStroke(Color.BLACK);
        cross2.setStroke(Color.BLACK);
        
        cross1.setStrokeWidth(3);  // Ustawienie szerokości linii
        cross2.setStrokeWidth(3);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(cross1, cross2);

        setGraphic(stackPane);

        setOnAction(event -> {
            if(gracz.dodaniePionka(nrpola, kolor)){
                toggleButtonState(this, kolor);
            }
        });
    }

    private void toggleButtonState(Button button, Color kolor) {
        Circle circle = new Circle(18, kolor);
        circle.setStroke(Color.BLACK);

        button.setGraphic(circle);
        button.setDisable(false);
    }
}
