package com.go.GUI;

import com.go.ObslugaPlanszy;

import javafx.application.Application;
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
        new GUI(stage, ob);
    }
}
