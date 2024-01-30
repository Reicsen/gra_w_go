package com.go.GUI;

import com.go.Gra.Klienci.Bot;
import com.go.Gra.Klienci.BrakSerwera;
import com.go.Gra.Klienci.Klient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
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
                //tworzymy bota i GuiPlansza dla gracza
                try
                {
                    bot = new Bot();
                    GridPane gridPane = new GuiPlansza();
                    stage.setTitle("Gra w go");

                    Scene scene = new Scene(gridPane, 1300, 1000);
                    stage.setScene(scene);
                    stage.show();
                }
                catch(BrakSerwera b)
                {
                    System.out.println("Nie ma serwera!");
                }
            }
        });
    }
}
