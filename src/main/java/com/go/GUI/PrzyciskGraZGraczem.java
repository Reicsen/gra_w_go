package com.go.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PrzyciskGraZGraczem extends Button{
    PrzyciskGraZGraczem(){
        super("Gra z innym graczem");

        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                
            }
        });
    }

    
    
}
