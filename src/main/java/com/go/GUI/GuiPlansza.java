package com.go.GUI;

import java.util.ArrayList;
import java.util.List;

import com.go.Gracz;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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
        lbl.setPrefWidth(965);
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
        b1.setPrefWidth(500);
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
        b2.setPrefWidth(480);
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
    public void oknoZTerenem(int twojeTerytorium, int wrogieTerytorium, int twoiJency, int przeciwniJency, int przeciwnePionyNaTwoimTerytorium, int twojePionyNaPrzeciwnymTerytorium){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Gra w go");

        // Panel dialogowy
        DialogPane dialogPane = dialog.getDialogPane();

        // Tworzymy etykietę do dodania do panelu dialogowego
        Label label = new Label("Twoje terytorium: " + twojeTerytorium + "\nPrzeciwne terytorium: "+ wrogieTerytorium+ "\nJeńcy należący do ciebie: "+ twoiJency+ "\nJeńcy należący do przeciwnika: "+ przeciwniJency+ "\nIlosc przeciwnych pionków na twoim terytorium: "+ przeciwnePionyNaTwoimTerytorium+ "\nIlosc twoich pionkow na przeciwwny terytorium: "+ twojePionyNaPrzeciwnymTerytorium + "\nCzy chcesz skończyć gre?" );

        // Dostosujemy styl etykiety 
        label.setFont(new Font(23));
        label.setTextAlignment(TextAlignment.CENTER);

        // Utwórz kontener na etykietę
        VBox vBox = new VBox(label);

        // Ustaw kontener jako zawartość panelu dialogowego
        dialogPane.setContent(vBox);

        // Dodaj przyciski
        ButtonType buttonTypeYes = new ButtonType("Tak");
        ButtonType buttonTypeNo = new ButtonType("Nie");

        // Dodaj przyciski do panelu dialogowego
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);

        // Obsługa zdarzenia po naciśnięciu przycisku 
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeYes) {
                // Obsługa zdarzenia po naciśnięciu przycisku "Tak"
                gracz.wybranoTak();
                Platform.runLater(() -> {
                    dialog.setResult("Tak");
                    dialog.close();
                });
            }
            else{
                // Obsługa zdarzenia po naciśnięciu przycisku "Nie"
                gracz.wybranoNie();
                Platform.runLater(() -> {
                    dialog.setResult("Nie");
                    dialog.close();
                });
            }
            return null;
        });

        Platform.runLater(() -> {
            dialog.showAndWait();
        });
    }
}
