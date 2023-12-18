package com.go;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Gra implements Runnable
{
    private String aktywnyKolor;
    private IPlansza plansza;
    private Socket gracz1;
    private Socket gracz2;

    public Gra(Socket s1, Socket s2)
    {
        this.aktywnyKolor="czarny";
        this.plansza=new Plansza();
        this.gracz1=s1;
        this.gracz2=s2;
    }

    private void zmienKolor()
    {
        if ("czarny".equals(this.aktywnyKolor))
        {
            aktywnyKolor="biały";
        }
        else
        {
            aktywnyKolor="czarny";
        }
    }

    private boolean sprawdzPoprawnosc(int nrpola)
    {
        return plansza.sprawdzPoprawnosc(this.aktywnyKolor, nrpola%19, nrpola/19);
    }

    private void dodajPionek(int nrpola)
    {
        plansza.dodajPionek(this.aktywnyKolor, nrpola%19, nrpola/19);
        this.zmienKolor();
    }

    @Override
    public void run()
    {
        try
        {
            DataInputStream odbieranieOdGracza1 = new DataInputStream(gracz1.getInputStream());
            DataOutputStream wysylanieDoGracza1 = new DataOutputStream(gracz1.getOutputStream());
            DataInputStream odbieranieOdGracza2 = new DataInputStream(gracz2.getInputStream());
            DataOutputStream wysylanieDoGracza2 = new DataOutputStream(gracz2.getOutputStream());

            wysylanieDoGracza1.writeInt(2);
            int sygnal;
            int ruch;

            while (true)
            {
                if ("czarny".equals(aktywnyKolor))
                {
                    sygnal=odbieranieOdGracza1.readInt();
                    if (sygnal==-1)
                    {
                        wysylanieDoGracza1.writeInt(0);
                        wysylanieDoGracza1.writeInt(-1);
                        wysylanieDoGracza2.writeInt(0);
                        wysylanieDoGracza2.writeInt(1);
                        break;
                    }
                    if (sygnal==0)
                    {
                        zmienKolor();
                        wysylanieDoGracza2.writeInt(2);
                    }
                    if (sygnal==1)
                    {
                        ruch=odbieranieOdGracza1.readInt();
                        if (sprawdzPoprawnosc(ruch))
                        {
                            wysylanieDoGracza1.writeInt(0);
                            wysylanieDoGracza1.writeInt(ruch);
                            wysylanieDoGracza2.writeInt(1);
                            wysylanieDoGracza2.writeInt(ruch);
                            dodajPionek(ruch);
                        }
                        else
                        {
                            wysylanieDoGracza1.writeInt(-1);
                        }
                    }
                }
                else
                {
                    sygnal=odbieranieOdGracza2.readInt();
                    if (sygnal==-1)
                    {
                        wysylanieDoGracza2.writeInt(0);
                        wysylanieDoGracza2.writeInt(-1);
                        wysylanieDoGracza1.writeInt(0);
                        wysylanieDoGracza1.writeInt(1);
                        break;
                    }
                    if (sygnal==0)
                    {
                        zmienKolor();
                        wysylanieDoGracza1.writeInt(2);
                    }
                    if (sygnal==1)
                    {
                        ruch=odbieranieOdGracza2.readInt();
                        if (sprawdzPoprawnosc(ruch))
                        {
                            wysylanieDoGracza2.writeInt(0);
                            wysylanieDoGracza2.writeInt(ruch);
                            wysylanieDoGracza1.writeInt(1);
                            wysylanieDoGracza1.writeInt(ruch);
                            dodajPionek(ruch);
                        }
                        else
                        {
                            wysylanieDoGracza2.writeInt(-1);
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
