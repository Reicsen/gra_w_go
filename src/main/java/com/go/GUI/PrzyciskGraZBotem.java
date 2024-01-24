package com.go.GUI;

import com.go.Bot;
import com.go.Klient;

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
                //tworzymy bota 
                bot = new Bot();

                //Tworzymy nowy Pane (który wyświetla plansze do gry) i zastępujemy stary nowym
                GridPane gridPane = new GuiPlansza();

                stage.setTitle("Gra w go");

                Scene scene = new Scene(gridPane, 1300, 1000);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
