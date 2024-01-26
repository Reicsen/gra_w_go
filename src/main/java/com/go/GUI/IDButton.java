package com.go.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class IDButton extends Button
{   
    public IDButton( String id, String nazwa, Stage stage)
    {
        super(nazwa);

        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                //Tworzymy nowy Pane (który wyświetla planszę do gry) i zastępujemy stary nowym
                GridPane gridPane1 = new GuiPlanszaOdtworzenia(Integer.parseInt(id));
                GridPane gridPane2 = new GuiPlanszaOdtworzenia(Integer.parseInt(id));

                stage.setTitle("Odtwarzanie gry");

                Scene scene = new Scene(gridPane1, 1300, 1000);
                stage.setScene(scene);
                stage.show(); 
            }
        });
    }
}
