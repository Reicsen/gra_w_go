package com.go.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

public class PrzyciskPionek extends Button{
    PrzyciskPionek(){
        super();
        setMaxSize(40, 40);

        Line cross1 = new Line(20, 0, 20, 40);
        Line cross2 = new Line(0, 20, 40, 20);
        cross1.setStroke(Color.BLACK);
        cross2.setStroke(Color.BLACK);
        
        cross1.setStrokeWidth(3);  // Ustawienie szerokości linii
        cross2.setStrokeWidth(3);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(cross1, cross2);

        setGraphic(stackPane);

        setOnAction(event -> {
            toggleButtonState(this);
        });
    }

    private void toggleButtonState(Button button) {
        Circle circle = new Circle(18, Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);

        button.setGraphic(circle);
        button.setDisable(true);
    }
}
