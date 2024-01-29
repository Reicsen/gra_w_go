package com.go.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.go.BazyDanych.IBazyDanychAdapter;
import com.go.BazyDanych.PobieranieDanychZBazy;

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

                    stage.close();
            }
        });
    }
}
