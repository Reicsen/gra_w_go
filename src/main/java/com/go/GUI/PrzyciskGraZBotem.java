package com.go.GUI;

import com.go.Gra.Klienci.Bot;
import com.go.Gra.Klienci.BrakSerwera;
import com.go.Gra.Klienci.Klient;

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
                try
                {
                    bot = new Bot();
                    stage.close();
                }
                catch(BrakSerwera b)
                {
                    System.out.println("Nie ma serwera!");
                }
            }
        });
    }
}
