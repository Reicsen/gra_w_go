package com.go.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrzyciskGraZGraczem extends Button{
    PrzyciskGraZGraczem(Stage stage){
        super("Gra z innym graczem");

        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                GridPane gridPane = new GuiPlansza();

                stage.setTitle("Gra w go");

                Scene scene = new Scene(gridPane, 1300, 1000);
                stage.setScene(scene);
                stage.show();
            }
        });
    }

    
    
}
