package com.go.GUI;

import javafx.scene.control.Button;

public class IDButton extends Button
{
    private int id;

    public IDButton(int nr, String nazwa)
    {
        this.id=nr;
        this.setText(nazwa);
    }

    public int zwrocId()
    {
        return this.id;
    }
}