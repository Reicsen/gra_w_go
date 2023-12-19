package com.go.GUI;

import com.go.ObslugaPlanszy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Aplikacja extends Application
{
    static ObslugaPlanszy ob;
    public static void main(ObslugaPlanszy gracz) 
    {
        ob = gracz;
        Application.launch();
    }

    @Override
    public void start(Stage stage) 
    {
        Platform.runLater(() -> {
            new GUI(stage, ob);
        });
    }
}
