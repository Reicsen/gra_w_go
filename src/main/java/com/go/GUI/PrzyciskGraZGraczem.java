package com.go.GUI;

import com.go.Gra.Klienci.BrakSerwera;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrzyciskGraZGraczem extends Button{

    //konsturktor przycisku
    PrzyciskGraZGraczem(Stage stage){

        //Stworzenie przycisku z tekstem w środku
        super("Gra z innym graczem");

        //Ustawienie co sie zdarzy po naciśnięciu przycisku
        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                //Tworzymy nowy Pane (który wyświetla plansze do gry) i zastępujemy stary nowym
                try
                {
                    GridPane gridPane = new GuiPlansza();

                    stage.setTitle("Gra w go");

                    Scene scene = new Scene(gridPane, 900, 1000);
                    stage.setScene(scene);
                    stage.show();
                }
                catch(BrakSerwera b)
                {
                    System.out.println("Brak serwera!");
                }
            }
        });
    }

    
    
}
