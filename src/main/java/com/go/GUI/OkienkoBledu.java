package com.go.GUI;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

public class OkienkoBledu
{
    public static void wyswietlBlad(String komunikat)
    {
        Dialog<String> okienko = new Dialog<String>();
        okienko.setContentText(komunikat);
        okienko.setWidth(400.0);
        okienko.setHeight(250.0);
        okienko.setResizable(false);
        okienko.setTitle("Wystąpił błąd");
        ButtonType zamkniecie = new ButtonType("OK",ButtonData.CANCEL_CLOSE);
        okienko.getDialogPane().getButtonTypes().add(zamkniecie);
    }
}
