package com.go.GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PrzyciskOdtworzGre extends Button
{
    //konsturktor przycisku
    PrzyciskOdtworzGre(Stage stage)
    {
        //Stworzenie przycisku z tekstem w środku
        super("Odtwarzanie gry");

        //Ustawienie co sie zdarzy po naciśnięciu przycisku
        setOnAction(new EventHandler<ActionEvent>() 
        { 
            @Override
            public void handle(ActionEvent e) 
            {
                IBazyDanychAdapter bazy = new BazyDanychAdapter();
                if(!bazy.start()){
                    wyskakujaceOkienko("Błąd: nie udane połączenie z bazą danych");
                }
                else{
                    GridPane wybor = new WyborGry(stage, bazy);
                    stage.setTitle("Odtwarzanie gry");

                    Scene scene = new Scene(wybor, 600, 1000);
                    stage.setScene(scene);
                    stage.show();
                }
                /*wybierzGre();
                while (true)
                {
                    if (kontrolka!=0)
                    {
                        break;
                    }                    
                }
                //Tworzymy nowy Pane (który wyświetla planszę do gry) i zastępujemy stary nowym
                GridPane gridPane1 = new GuiPlanszaOdtworzenia(kontrolka);
                GridPane gridPane2 = new GuiPlanszaOdtworzenia(kontrolka);

                stage.setTitle("Odtwarzanie gry");

                Scene scene = new Scene(gridPane1, 1300, 1000);
                stage.setScene(scene);
                stage.show();
                */
            }
        });
    }

    /*private void wybierzGre()
    {
        try (Connection polaczenie = DriverManager.getConnection("jdbc:mariadb://localhost:3306/gra_w_go", "user", "");
            Statement kwerenda = polaczenie.createStatement();)
        {
            kwerenda.executeQuery("CALL start();");
            ResultSet wyniki = kwerenda.executeQuery("EXECUTE lista_gier;");
            GridPane siatka = new GridPane();
            Stage baza = new Stage();

            Label kol1 = new Label("Id gry");
            kol1.setFont(Font.font("Callibri",FontWeight.BOLD,20));
            kol1.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null)));
            kol1.setWrapText(true);
            kol1.setTextAlignment(TextAlignment.CENTER);
            kol1.setMinWidth(300);
            kol1.setMaxWidth(300);
            kol1.setMinHeight(30);
            kol1.setMaxHeight(30);

            Label kol2 = new Label("Zwycięzca");
            kol2.setFont(Font.font("Callibri",FontWeight.BOLD,20));
            kol2.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,null,null)));
            kol2.setWrapText(true);
            kol2.setTextAlignment(TextAlignment.CENTER);
            kol2.setMinWidth(300);
            kol2.setMaxWidth(300);
            kol2.setMinHeight(30);
            kol2.setMaxHeight(30);

            siatka.addRow(0, kol1,kol2);

            EventHandler<ActionEvent> kliknieto = new EventHandler<ActionEvent>()
            {
                @Override
                public void handle (ActionEvent event)
                {
                    IDButton zrodlo = (IDButton) event.getSource();
                    kontrolka = zrodlo.zwrocId();
                    baza.close();
                }
            };

            int ilosc=1;
            while(wyniki.next())
            {
                int id = wyniki.getInt("id");
                String wygrany = wyniki.getString("wygrany");
                
                IDButton temp1 = new IDButton(id,Integer.toString(id));
                temp1.setFont(Font.font("Callibri",FontWeight.BOLD,20));
                temp1.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,null,null)));
                temp1.setWrapText(true);
                temp1.setTextAlignment(TextAlignment.CENTER);
                temp1.setMinWidth(300);
                temp1.setMaxWidth(300);
                temp1.setMinHeight(30);
                temp1.setMaxHeight(30);

                IDButton temp2 = new IDButton(id,wygrany);
                temp2.setFont(Font.font("Callibri",FontWeight.BOLD,20));
                temp2.setBackground(new Background(new BackgroundFill(Color.MISTYROSE,null,null)));
                temp2.setWrapText(true);
                temp2.setTextAlignment(TextAlignment.CENTER);
                temp2.setMinWidth(300);
                temp2.setMaxWidth(300);
                temp2.setMinHeight(30);
                temp2.setMaxHeight(30);

                temp1.setOnAction(kliknieto);
                temp2.setOnAction(kliknieto);

                siatka.addRow(wyniki.getRow(), temp1,temp2);
                ilosc=ilosc+1;
            }

            Scene scena = new Scene(siatka);
            baza.setTitle("");
            siatka.setMinWidth(600);
            siatka.setMaxWidth(600);
            siatka.setMinHeight((ilosc+1)*30+40);
            siatka.setMaxHeight((ilosc+1)*30+40);
            baza.setScene(scena);
            baza.setMinWidth(600);
            baza.setMaxWidth(600);
            baza.setMinHeight((ilosc+1)*30+40);
            baza.setMaxHeight((ilosc+1)*30+40);
            baza.show();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }*/
    private void wyskakujaceOkienko(String kontekst){

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
