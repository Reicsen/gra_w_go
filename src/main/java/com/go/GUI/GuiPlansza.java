package com.go.GUI;

import java.util.ArrayList;
import java.util.List;

import com.go.Gracz;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GuiPlansza extends GridPane 
{
    private Gracz gracz;
    public Label lbl;
    public List<PrzyciskPionek> pionki= new ArrayList<>();
    public Color kolor;

    public GuiPlansza()
    {
        //Tworzymy nowy GridPane
        super();
        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");

        //Tworzymy nowego gracza
        gracz = new Gracz(this);
    
        //Tworzymy label na którym będą wyświetlać się komunikaty
        lbl = new Label();
        lbl.setPrefWidth(930);
        lbl.setPrefHeight(50);
        lbl.setStyle("-fx-background-color: white;");
        lbl.setFont(Font.font(20));
        lbl.setText(" Czekanie na drugiego gracza");
        setColumnSpan(lbl, 19); // Rozciąganie przez 16 kolumn
        add(lbl, 0, 0);

        //Tworzymy przyciski, które po wciśnięciu dodają pionek w danym miejscu
        //Przycisków jest tyle ile przecięć linji w planszy
        for (int row = 1; row < 20; row++) {
            for (int col = 0; col < 19; col++) {
                PrzyciskPionek button = new PrzyciskPionek(gracz, row-1, col);
                button.setStyle("-fx-background-color: white;");

                add(button, col, row);

                setHalignment(button, HPos.CENTER);

                pionki.add(button);
                button.setDisable(true);
            }
        }

        //Dodajemy przycisk który po naciśnięciu powoduje poddanie się gracza
        Button b1 = new Button("Poddaj się");
        b1.setPrefWidth(450);
        b1.setPrefHeight(50);
        b1.setStyle("-fx-background-color: aquamarine");
        b1.setFont(Font.font(20));
        setColumnSpan(b1, 10); 
        add(b1, 0, 20);
        b1.setOnAction(event -> {
            gracz.poddajSie();
        });

        //Dodajemy przycisk który po naciśnięciu powoduje pominięcie ruchu przez gracza
        Button b2 = new Button("Pomiń ruch");
        b2.setPrefWidth(420);
        b2.setPrefHeight(50);
        b2.setStyle("-fx-background-color: turquoise");
        b2.setFont(Font.font(20));
        setColumnSpan(b2, 9);
        add(b2, 10, 20);
        b2.setOnAction(event -> {
            gracz.pominRuch();
        });
    }
    //Funkcja, która wywoływana jest na początku rozgrywki
    //Uruchamia ona przyciski dostawiające pionki
    public void rozpoczecieGry(){
        Platform.runLater(() -> {
        for (Button b : pionki) {
            b.setDisable(false);
        }
    });
    }

    public void wyskakujaceOkienko(String kontekst){

        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Gra w go");
        ButtonType type = new ButtonType("Ok", ButtonData.CANCEL_CLOSE);
        //dialog.setContentText(kontekst);

        // Panel dialogowy
        DialogPane dialogPane = dialog.getDialogPane();

        // Tworzymy etykietę do dodania do panelu dialogowego
        Label label = new Label(kontekst);

        // Dostosujemy styl etykiety 
        label.setFont(new Font(23));
        label.setTextAlignment(TextAlignment.CENTER);

        // Utwórz kontener na etykietę
        VBox vBox = new VBox(label);

        // Ustaw kontener jako zawartość panelu dialogowego
        dialogPane.setContent(vBox);

        // Dodaj przycisk "Ok"
        dialog.getDialogPane().getButtonTypes().add(type);

        Platform.runLater(() -> {
            dialog.showAndWait();
        });
    }
    public void zaznaczTeren(List<String> lista){
        Stage stage = new Stage();
        GridPane pane = new Teren(lista, gracz, stage);
        stage.setTitle("Zaznacz teren "+ kolor());
        Scene scene = new Scene(pane, 895, 1000);
        stage.setScene(scene);
        stage.show(); 
    }
    
    public void  zgoda(List<String> lista){
        System.out.println("funkcja");
        Stage stage = new Stage();
        System.out.println("stage");
        GridPane pane = new Zgoda(lista, gracz, stage, this);
        System.out.println("zgoda");
        stage.setTitle("Negocjacje "+ kolor());
        Scene scene = new Scene(pane, 890, 1000);
        stage.setScene(scene);
        stage.show(); 
    }
    private String kolor(){
        String s;
        if(kolor == Color.WHITE){
            s = "biały";
        }
        else{
            s = "czarny";
        }
        return s;
    }
}
