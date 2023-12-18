package com.go.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PrzyciskGraZBotem extends Button{
    PrzyciskGraZBotem(){
        super("Gra z botem");

        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                
            }
        });
    }
}
