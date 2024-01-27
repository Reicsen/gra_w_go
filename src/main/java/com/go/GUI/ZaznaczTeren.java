package com.go.GUI;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class ZaznaczTeren extends Button{

    public int nrpola;
    public int zaznaczone;
    public List <Integer> terenZaznaczony = new ArrayList<>(); 
    public Paint paint = Paint.valueOf("violet");

    public ZaznaczTeren(String wygląd, int nrpola, Teren teren){
        super();
        setMaxSize(26, 26);
        this.nrpola = nrpola;
        zaznaczone = 0;

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

        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                if(zaznaczone == 0){
                    terenZaznaczony.add(nrpola);
                    Rectangle rec = new Rectangle(26, 26, paint);    

                    setGraphic(rec);
                    zaznaczone = 1;
                    System.out.println("zaznaczono");
                }
                else{
                    if(terenZaznaczony.contains(nrpola)){
                        terenZaznaczony.remove(terenZaznaczony.indexOf(nrpola));
                        Line cross1 = new Line(13, 0, 13, 26);
                        Line cross2 = new Line(0, 13, 26, 13);
                        cross1.setStroke(Color.BLACK);
                        cross2.setStroke(Color.BLACK);
                                
                        cross1.setStrokeWidth(3);  
                        cross2.setStrokeWidth(3);

                        StackPane stackPane = new StackPane();
                        stackPane.getChildren().addAll(cross1, cross2);

                        setGraphic(stackPane);
                        System.out.println("grafika");
                        
                        zaznaczone = 0;
                    }
                    
                    System.out.println("po grafice");
                }
            }
        });
    }
    public void ustawTeren(List <Integer> teren){
        this.terenZaznaczony = teren;
    }
    public void ustawPaint(Paint paint){
        this.paint = paint;
    }
}
