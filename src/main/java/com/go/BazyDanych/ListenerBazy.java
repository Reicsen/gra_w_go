package com.go.BazyDanych;

import java.util.ArrayList;
import java.util.List;

public class ListenerBazy implements IListenerBazy
{
    private List<Integer> gracze;
    private List<Integer> pola;

    public ListenerBazy()
    {
        this.gracze=new ArrayList<Integer>();
        this.pola=new ArrayList<Integer>();
    }

    public void dodaj(int gracz, int pole)
    {
        this.gracze.add(gracz);
        this.pola.add(pole);
        if (pole==-1)
        {
            koniec(gracz); //gracz to ten, który się poddał
        }
    }

    public void koniec(int gracz)
    {
        IBazyDanychAdapter zapis;
        if (gracz==1)
        {
            zapis = new ZapisDoBazy(gracze,pola ,"bialy");
        }
        else
        {
            zapis = new ZapisDoBazy(gracze,pola ,"czarny");
        }
        zapis.obsluz();
    }
}
