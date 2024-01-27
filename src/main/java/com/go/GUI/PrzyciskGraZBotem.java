package com.go.GUI;

import com.go.Bot;
import com.go.Klient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrzyciskGraZBotem extends Button{

    private Klient bot;

    PrzyciskGraZBotem(Stage stage){
        super("Gra z botem");

        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                //tworzymy bota 
                bot = new Bot();
                stage.close();
            }
        });
    }
}
