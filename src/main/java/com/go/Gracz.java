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

public class Gracz implements Klient, ObslugaPlanszy, INegocjacje, Runnable
{
    private boolean aktywny=false;
    private int iloscJencow=0;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    private Socket polaczenieZSerwerem;
    private GuiPlansza plansza;
    private boolean kontrolkaOkienka;
    public List<String> lista;

    public Gracz(GuiPlansza plansza) //konstruktor; reszta metod opisana w interfejsach; sygnały informacyjne zawarte zostały w pliku Sygnały.txt
    {
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            int nrGracza = odbieranieOdSerwera.readInt();
            this.kontrolkaOkienka=false;
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
        try
        {
            this.aktywny=false;
            int wlasneTerytorium = odbieranieOdSerwera.readInt();
            int przeciwneTerytorium = odbieranieOdSerwera.readInt();
            int przeciwnePionkiNaWlasnymTerytorium = odbieranieOdSerwera.readInt();
            int wlasnePionkiNaPrzeciwnymTerytorium = odbieranieOdSerwera.readInt();  
            int jencyRywala = odbieranieOdSerwera.readInt();          
            
            Platform.runLater(() -> {
                plansza.zgoda(lista);
                //plansza.zaznaczTeren(lista);
                //plansza.oknoZTerenem(wlasneTerytorium, przeciwneTerytorium, iloscJencow, jencyRywala, przeciwnePionkiNaWlasnymTerytorium, wlasnePionkiNaPrzeciwnymTerytorium);
            });
            while (!this.kontrolkaOkienka)
            {
                wysylanieDoSerwera.writeInt(2);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void wybranoTak(int twojeTerytorium, int jencyPrzeciwnika, int twojePionkiNaTereniePrzeciwnika)
    {
        try
        {
            this.kontrolkaOkienka=true;
            wysylanieDoSerwera.writeInt(3);
            wysylanieDoSerwera.writeInt(1);
            wysylanieDoSerwera.writeInt(Math.max(0, twojeTerytorium-(jencyPrzeciwnika+twojePionkiNaTereniePrzeciwnika)));
            this.kontrolkaOkienka=false;
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
            this.kontrolkaOkienka=true;
            wysylanieDoSerwera.writeInt(3);
            wysylanieDoSerwera.writeInt(-1);
            this.kontrolkaOkienka=false;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    //Po tym jak gracz uzgodni terytorium wywoływana jest ta funkcja
    public void wyslijTeren(List <Integer> twojTeren, List <Integer> przeciwnikaTeren){
        //TODO
       
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

                if(sygnal==6)
                {
                    wysylanieDoSerwera.writeInt(iloscJencow);
                }
                if (sygnal==101)
                {
                    dane();
                }             
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void ustawListe(){
        this.lista = new ArrayList<>();
        for(int i = 0; i < 361; i++){
            lista.add("null");
        }
    }
    public List<String> podajListe(){
        return lista;
    }
    private void zmien(int nrpola, Color kolor){
        if(kolor == Color.WHITE){
            lista.remove(nrpola);
            lista.add(nrpola, "biały");
        }
        else{
            lista.remove(nrpola);
            lista.add(nrpola, "czarny");
        }
    }
    private void usun(int nrpola){
        lista.remove(nrpola);
        lista.add(nrpola, "null");
    }
}
