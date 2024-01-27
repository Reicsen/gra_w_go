package com.go.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class StworzButton implements FactoryButton{

    public Button przycisk(Integer cecha){

        Button btn = new Button();
        btn.setMaxSize(26, 26);

        if(cecha==0){
            Line cross1 = new Line(13, 0, 13, 26);
            Line cross2 = new Line(0, 13, 26, 13);
            cross1.setStroke(Color.BLACK);
            cross2.setStroke(Color.BLACK);
            
            cross1.setStrokeWidth(3);  
            cross2.setStrokeWidth(3);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(cross1, cross2);

            btn.setGraphic(stackPane);
        }
        else if(cecha==2){
            Circle circle = new Circle(13, Color.WHITE);
            
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(3);

            btn.setGraphic(circle);
        }
        else if(cecha==1){
            Circle circle = new Circle(13, Color.BLACK);
            
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(3);

            btn.setGraphic(circle);
        }
        else if(cecha==3){
            Rectangle rec = new Rectangle(26, 26, Paint.valueOf("violet"));    

            btn.setGraphic(rec);
        }
        else if(cecha==4){
            Rectangle rec = new Rectangle(26, 26, Paint.valueOf("blue"));    

            btn.setGraphic(rec);
        }
        
        btn.setStyle("-fx-background-color: white;");
        return btn;
    }
}
