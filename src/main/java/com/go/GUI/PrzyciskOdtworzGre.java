package com.go.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrzyciskOdtworzGre extends Button {
    public PrzyciskOdtworzGre(Stage stage){
        super("Odtworzenie gry");
        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                
            }
        });
    }
}
