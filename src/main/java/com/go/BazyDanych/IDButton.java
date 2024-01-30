package com.go.BazyDanych;

import javafx.scene.control.Button;

public class IDButton extends Button
{
    private int id;

    public IDButton(int nr, String nazwa)
    {
        this.id=nr;
        this.setText(nazwa);
    }

    public IDButton(int nr)
    {
        this.id=nr;
        this.setText(Integer.toString(nr));
    }

    public int zwrocId()
    {
        return this.id;
    }
}