package com.go.Gra;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serwer
{
    public Serwer()
    {
        try
        {
            ServerSocket serwerSocket = new ServerSocket(8000); //stworzenie serwera
            System.out.println("Oczekiwanie na graczy...");
            Socket gracz1 = serwerSocket.accept(); //odebranie połączenia od 1. gracza
            System.out.println("Dołączył 1. gracz.");
            new DataOutputStream(gracz1.getOutputStream()).writeInt(1); //wysłanie graczowi, że jest pierwszy
            Socket gracz2 = serwerSocket.accept(); //odebranie połączenia od 2. gracza
            System.out.println("Dołączył 2. gracz."); 
            new DataOutputStream(gracz2.getOutputStream()).writeInt(2); //wysłanie graczowi, że jest drugi
            Gra gra = new Gra(gracz1, gracz2);
            Thread watek = new Thread(gra);
            watek.start(); //stworzenie nowej gry i uruchomienie wątku
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    { 
        new Serwer();      
    }
}
