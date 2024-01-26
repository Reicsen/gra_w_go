package com.go.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class ZaznaczTeren extends Button{
    public ZaznaczTeren(String wygląd){
        super();
        setMaxSize(26, 26);

        if(wygląd.equals("null")){
            Line cross1 = new Line(13, 0, 13, 26);
            Line cross2 = new Line(0, 13, 26, 13);
            cross1.setStroke(Color.BLACK);
            cross2.setStroke(Color.BLACK);
            
            cross1.setStrokeWidth(3);  
            cross2.setStrokeWidth(3);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(cross1, cross2);

            setGraphic(stackPane);
        }
        else{
            Circle circle;

            if(wygląd.equals("biały")){
                circle = new Circle(13, Color.WHITE);
            }
            else{
                circle = new Circle(13, Color.BLACK);
            }
        
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(3);

            setGraphic(circle);
            setDisable(true);
        }

    }
}
