package com.go.GUI;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import com.go.Gra.Klienci.Klient;

public class PrzyciskPionek extends Button
{
    PrzyciskPionek(Klient gracz, int x, int y){

        //Stworzenie przycisku który ma na sobie grafike (krzyż)
        super();
        setMaxSize(26, 26);

        Line cross1 = new Line(13, 0, 13, 26);
        Line cross2 = new Line(0, 13, 26, 13);
        cross1.setStroke(Color.BLACK);
        cross2.setStroke(Color.BLACK);
        
        cross1.setStrokeWidth(3);  
        cross2.setStrokeWidth(3);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(cross1, cross2);

        setGraphic(stackPane);

        //Ustawienie akcji że po naciśnięciu w przycisk gracz spróbuje postawić pionek w to miejsce
        setOnAction(event -> {
            gracz.wykonajRuch(y, x);
        });
    }

    //Funkcja która zmienia grafike na przycisku
    //krzyż zmieniany jest na koło
    //Ta funkcja jest potrzebna aby po udanym postawieniu pionka w dane miejsce dało się "zobaczyć" na planszy, że jest tam pionek
    public void zmienPrzyciskNaKolo(Button button, Color kolor) {
        Circle circle = new Circle(13, kolor);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);

        button.setGraphic(circle);
    }

    //Funkcja która zmienia grafike na przycisku
    //koło zmieniane jest na krzyż
    //Ta funkcja jest potrzebna aby po usunięciu pionka dało się "zobaczyć" na planszy, że go tam nie ma
    public void zmienPrzyciskNaKrzyzyk(Button button){
        Line cross1 = new Line(13, 0, 13, 26);
        Line cross2 = new Line(0, 13, 26, 13);
        cross1.setStroke(Color.BLACK);
        cross2.setStroke(Color.BLACK);
        
        cross1.setStrokeWidth(3);  
        cross2.setStrokeWidth(3);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(cross1, cross2);

        button.setGraphic(stackPane);
    }
}
