package com.go.GUI;

import java.util.ArrayList;
import java.util.List;

import com.go.Odtworzenie;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
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

public class GuiPlanszaOdtworzenia extends GridPane 
{
    private Odtworzenie gracz;
    public List<PrzyciskPionek> pionki= new ArrayList<>();
    public Color kolor;

    public GuiPlanszaOdtworzenia(List<Integer> ruchy, List<Integer> gracze)
    {
        //Tworzymy nowy GridPane
        super();
        setAlignment(Pos.CENTER);
        setHgap(0);
        setVgap(0);
        setStyle("-fx-background-color: black;");

        //Tworzymy nowego gracza
        gracz = new Odtworzenie(this,ruchy,gracze);

        //Tworzymy przyciski, które będą reprezentować pionki lub przecięcia linii
        //Przycisków jest tyle ile przecięć linii w planszy
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
}
