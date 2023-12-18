package com.go.GUI;

import javafx.application.Application;
import javafx.stage.Stage;

public class Aplikacja extends Application
{
    public static void main(String[] args) 
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) 
    {
        new MyGui(stage);
    }
}
