package com.go.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrzyciskOdtworzGre extends Button
{
    //konsturktor przycisku
    PrzyciskOdtworzGre(Stage stage)
    {
        //Stworzenie przycisku z tekstem w środku
        super("Odtwarzanie gry");

        //Ustawienie co sie zdarzy po naciśnięciu przycisku
        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                    IBazyDanychAdapter pobierzDane = new PobieranieDanychZBazy();
                    pobierzDane.obsluz();

                    GridPane gridPane = new GuiPlansza();
                    stage.setTitle("Odtwarzanie gry");

                    Scene scene = new Scene(gridPane, 600, 1000);
                    stage.setScene(scene);
                    stage.show();
            }
        });
    }
}
