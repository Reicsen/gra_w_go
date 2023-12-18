package com.go.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;

public class GUI {
    public GUI(Stage stage){
    
        stage.setTitle("Figures");

        BorderPane root = new BorderPane();
        Pane pane = new Pane();

        Menu menu1 = new Menu("Info");
        Menu menu2 = new Menu("Figures");

        MenuBar myMenu = new MenuBar(menu1, menu2, pane);


        root.setTop(myMenu);
        root.setCenter(pane);

        Scene scene = new Scene(root, 1900, 800);
        stage.setScene(scene);
        stage.show(); 

    }
    
}
