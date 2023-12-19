package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.go.GUI.Aplikacja;
import com.go.GUI.GuiPlansza;
import javafx.scene.paint.Color;

public class Gracz implements Klient, ObslugaPlanszy, Runnable
{
    private boolean aktywny=false;
    private int iloscJencow=0;
    private Color kolor=null;
    private Color kolorPrzeciwnika=null;
    private DataInputStream odbieranieOdSerwera;
    private DataOutputStream wysylanieDoSerwera;
    Socket polaczenieZSerwerem;

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
        }
        else
        {
            this.kolor=Color.WHITE;
            this.kolorPrzeciwnika=Color.BLACK;
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

    public Gracz()
    {
        wlaczGUI();
        try
        {
            this.polaczenieZSerwerem = new Socket("localhost", 8000);
            this.odbieranieOdSerwera = new DataInputStream(polaczenieZSerwerem.getInputStream());
            this.wysylanieDoSerwera = new DataOutputStream(polaczenieZSerwerem.getOutputStream());
            int nrGracza = odbieranieOdSerwera.readInt();
            ustawKolor(nrGracza);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Thread watek = new Thread(this);
        watek.start();
    }

    public void wlaczGUI()
    {
        new Aplikacja().main(this);
    }

    GuiPlansza plansza;
    @Override
    public void setGuiPlansza(GuiPlansza plansza) {
        this.plansza=plansza;
    }

    /*
     * Funkcja zwraca true jeśli udało się dodać pionek do Planszy 
     * Funkcja zwaraca false jeśli nie udało się dodać pionka do planszy
     */
    public boolean dodaniePionka(int nrpola, Color kolor)
    {
        return true;
    }

    public void usunieciePionka(int nrpola)
    {
    
    }

    /*
     * Funkcja wypisuje komunikat na planszy 
     */
    public void wypiszKomunikatNaPlanszy(String komunikat)
    {
        plansza.lbl.setText(komunikat);
    }

    /*
     * Funkcja zwraca true jeśli do serwera dołączyło dwóch graczy
     * Funkcja zwraca false jeśli jeszcze czekamy na kolejnego gracza
     */
    public boolean rozpoczniGre()
    {
        return true;
    }

    public static void main(String[] args)
    {
        new Gracz(); 
    }

    @Override
    public void run()
    {
        int sygnal;
        int ruch;

        while (true)
        {
            try
            {
                sygnal=odbieranieOdSerwera.readInt();
                if (aktywny)
                {
                    if (sygnal==0)
                    {
                        ruch=odbieranieOdSerwera.readInt();
                        dodaniePionka(ruch, kolor);
                        zmienAktywnosc();
                        wypiszKomunikatNaPlanszy("Tura przeciwnika");
                    }
                    else
                    {
                        wypiszKomunikatNaPlanszy("Niepoprawny ruch, spróbuj ponownie");
                    }
                }
                else
                {
                    if (sygnal==0)
                    {
                        ruch=odbieranieOdSerwera.readInt();
                        if (ruch==-1)
                        {
                            wypiszKomunikatNaPlanszy("Przegrałeś!");
                            break;
                        }
                        else
                        {
                            wypiszKomunikatNaPlanszy("Wygrałeś!");
                            break;
                        }
                    }
                    if (sygnal==2)
                    {
                        zmienAktywnosc();
                        wypiszKomunikatNaPlanszy("Twoja tura");
                    }
                    if (sygnal==1)
                    {
                        ruch=odbieranieOdSerwera.readInt();
                        dodaniePionka(ruch, kolorPrzeciwnika);
                        zmienAktywnosc();
                        wypiszKomunikatNaPlanszy("Twoja tura");
                    }
                    if (sygnal==-1)
                    {
                        ruch=odbieranieOdSerwera.readInt();
                        while (ruch!=-1)
                        {
                            usunieciePionka(ruch);
                            ruch=odbieranieOdSerwera.readInt();
                        }
                    }
                    if(sygnal==10)
                    {
                        plansza.rozpoczecieGry();
                        wypiszKomunikatNaPlanszy("Tura przeciwnika");
                    }
                    //na razie obsługa jeńców nie jest implementowana, bo i tak mechanika ich nie obejmuje
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
