package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.go.GUI.GuiPlansza;
import javafx.application.Platform;
import javafx.scene.paint.Color;

public class Gracz implements Klient, ObslugaPlanszy, INegocjacje, PrzesylanieTerytoriow, Runnable
{
    private boolean aktywny=false;
    private int iloscJencow=0;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    private GuiPlansza plansza;
    private List<Integer> negocjacyjneWlasneTerytorium;
    private List<Integer> negocjacyjneWrogieTerytorium;
    public List<Integer> lista;

    public Gracz(GuiPlansza plansza) //konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            int nrGracza = odbieranieOdSerwera.readInt();
            this.negocjacyjneWlasneTerytorium = new ArrayList<Integer>();
            this.negocjacyjneWrogieTerytorium = new ArrayList<Integer>();
            this.plansza=plansza;
            ustawListe();
            
            ustawKolor(nrGracza);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Thread watek = new Thread(this); //stworzenie wątku
        watek.start();
    }


    public void poddajSie()
    {
        if (aktywny)
        {
            try
            {
                zmienAktywnosc();
                wysylanieDoSerwera.writeInt(-1);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void pominRuch()
    {
        if (aktywny)
        {
            try
            {
                zmienAktywnosc();
                wysylanieDoSerwera.writeInt(0);
                wypiszKomunikatNaPlanszy("Tura przeciwnika");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void wykonajRuch(int x, int y)
    {
        if (aktywny)
        {
            try
            {
                wysylanieDoSerwera.writeInt(1);
                wysylanieDoSerwera.writeInt(x+19*y);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void ustawKolor(int nrGracza)
    {
        if (nrGracza==1)
        {
            this.kolor=Color.BLACK;
            this.kolorPrzeciwnika=Color.WHITE;
            plansza.kolor=Color.BLACK;
        }
        else
        {
            this.kolor=Color.WHITE;
            this.kolorPrzeciwnika=Color.BLACK;
            plansza.kolor=Color.WHITE;
        }
    }

    public void zmienAktywnosc()
    {
        if (aktywny)
        {
            this.aktywny=false;
        }
        else
        {
            this.aktywny=true;
        }
    }


    public void dodaniePionka(int nrpola, Color kolor)
    {
        Platform.runLater(() -> {
        plansza.pionki.get(nrpola).zmienPrzyciskNaKolo( plansza.pionki.get(nrpola), kolor);
        });
        zmien(nrpola, kolor);
        System.out.println(nrpola);
        System.out.println(nrpola/19);
        System.out.println(nrpola%19);
    }

    public void usunieciePionka(int nrpola)
    {
        Platform.runLater(() -> {
            plansza.pionki.get(nrpola).zmienPrzyciskNaKrzyzyk( plansza.pionki.get(nrpola));
        });
        usun(nrpola);
    }

    public void wypiszKomunikatNaPlanszy(String komunikat)
    {
        Platform.runLater(() -> {
            plansza.lbl.setText(komunikat);
        });
    }

    public void okienko(String komunikat){
        Platform.runLater(() -> {
            plansza.wyskakujaceOkienko(komunikat);
        });
        wypiszKomunikatNaPlanszy(komunikat);
    }

    public void dane()
    {
        Platform.runLater(() -> {
            plansza.zgoda(lista);
        });
    }

    public void wybranoTak()
    {
        try
        {
            wysylanieDoSerwera.writeInt(3);
            wysylanieDoSerwera.writeInt(1); //zaakceptowanie planszy negocjacyjnej
            for (int i=0; i<negocjacyjneWlasneTerytorium.size(); i++)
            {
                int temp=negocjacyjneWlasneTerytorium.get(i);
                wysylanieDoSerwera.writeInt(temp);
            }
            wysylanieDoSerwera.writeInt(-1);
            for (int i=0; i<negocjacyjneWrogieTerytorium.size(); i++)
            {
                int temp=negocjacyjneWrogieTerytorium.get(i);
                wysylanieDoSerwera.writeInt(temp);
            }
            wysylanieDoSerwera.writeInt(-1);
            negocjacyjneWlasneTerytorium = new ArrayList<Integer>();
            negocjacyjneWrogieTerytorium = new ArrayList<Integer>();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void wybranoNie()
    {
        try
        {
            wysylanieDoSerwera.writeInt(3);
            wysylanieDoSerwera.writeInt(-1); //odrzucenie planszy negocjacyjnej
            negocjacyjneWlasneTerytorium = new ArrayList<Integer>();
            negocjacyjneWrogieTerytorium = new ArrayList<Integer>();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //Po tym jak gracz uzgodni terytorium wywoływana jest ta funkcja
    public void wyslijTeren(List <Integer> twojTeren, List <Integer> przeciwnikaTeren)
    {
        try
        {
            wysylanieDoSerwera.writeInt(100);
            for (int i=0; i<twojTeren.size(); i++)
            {
                int temp=twojTeren.get(i);
                wysylanieDoSerwera.writeInt(temp);
            }
            wysylanieDoSerwera.writeInt(-1);
            for (int i=0; i<przeciwnikaTeren.size(); i++)
            {
                int temp=przeciwnikaTeren.get(i);
                wysylanieDoSerwera.writeInt(temp);
            }
            wysylanieDoSerwera.writeInt(-1);
            negocjacyjneWlasneTerytorium=twojTeren;
            negocjacyjneWrogieTerytorium=przeciwnikaTeren;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void odbierzTeren()
    {
        negocjacyjneWlasneTerytorium = new ArrayList<Integer>();
        negocjacyjneWrogieTerytorium = new ArrayList<Integer>();
        int pole;

        try
        {
            pole=odbieranieOdSerwera.readInt();
            while (pole!=-1)
            {
                negocjacyjneWrogieTerytorium.add(pole);
                pole=odbieranieOdSerwera.readInt();
            }
            pole=odbieranieOdSerwera.readInt();
            while (pole!=-1)
            {
                negocjacyjneWlasneTerytorium.add(pole);
                pole=odbieranieOdSerwera.readInt();
            }
            List<Integer> listaNegocjacyjna = lista;
            for (int i=0; i< negocjacyjneWlasneTerytorium.size(); i++)
            {
                int temp=negocjacyjneWlasneTerytorium.get(i);
                listaNegocjacyjna.set(temp,3);
            }
            for (int i=0; i< negocjacyjneWrogieTerytorium.size(); i++)
            {
                int temp=negocjacyjneWrogieTerytorium.get(i);
                listaNegocjacyjna.set(temp,4);
            }
            Platform.runLater(() -> {
                plansza.zgoda(listaNegocjacyjna);
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    

    @Override
    public void run() //metoda obsługująca działanie wątkowe - odbieranie sygnałów z serwera i uruchamianie odpowiednich metod z interfejsów
    {
        int sygnal;
        int ruch;

        while (true)
        {
            try
            {
                sygnal=odbieranieOdSerwera.readInt();
                if (sygnal==0)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    dodaniePionka(ruch, kolor);
                    zmienAktywnosc();
                    wypiszKomunikatNaPlanszy("Tura przeciwnika");
                }
                if (sygnal == -1)
                {
                    okienko("Niepoprawny ruch, spróbuj ponownie");
                }

                if (sygnal==1)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    dodaniePionka(ruch, kolorPrzeciwnika);
                    zmienAktywnosc();
                    wypiszKomunikatNaPlanszy("Twoja tura");
                }
                if (sygnal==2)
                {
                    zmienAktywnosc();
                    wypiszKomunikatNaPlanszy("Twoja tura");
                }

                if(sygnal==3)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    this.iloscJencow=this.iloscJencow+ruch;
                }
                if (sygnal==4)
                {
                    ruch=odbieranieOdSerwera.readInt();
                    while (ruch!=-1)
                    {
                        usunieciePionka(ruch);
                        ruch=odbieranieOdSerwera.readInt();
                    }
                }  
                       
                if (sygnal==5)
                {
                    ruch=odbieranieOdSerwera.readInt();                    
                    if (ruch==-1)
                    {
                        okienko("Przegrałeś :C");
                        break;
                    }
                    else
                    {
                        okienko("Wygrałeś!");
                        break;
                    }
                }                
                if(sygnal==10)
                {
                    plansza.rozpoczecieGry();
                    wypiszKomunikatNaPlanszy("Tura przeciwnika");
                    if(Color.BLACK.equals(this.kolor))
                    {
                        wysylanieDoSerwera.writeInt(0); //to nie odczyt z bazy
                    }
                }

                if (sygnal==6)
                {
                    dane();
                }
                if(sygnal==100)
                {
                    odbierzTeren();
                }
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void ustawListe(){
        this.lista = new ArrayList<Integer>();
        for(int i = 0; i < 361; i++){
            lista.add(0);
        }
    }
    public List<Integer> podajListe(){
        return lista;
    }
    private void zmien(int nrpola, Color kolor){
        if(kolor == Color.WHITE){
            lista.remove(nrpola);
            lista.add(nrpola, 2);
        }
        else{
            lista.remove(nrpola);
            lista.add(nrpola, 1);
        }
    }
    private void usun(int nrpola){
        lista.remove(nrpola);
        lista.add(nrpola, 0);
    }
}
