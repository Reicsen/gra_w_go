package com.go;

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
            ServerSocket serwerSocket = new ServerSocket(8000);
            System.out.println("Oczekiwanie na graczy...");
            Socket gracz1 = serwerSocket.accept();
            System.out.println("Dołączył 1. gracz.");
            new DataOutputStream(gracz1.getOutputStream()).writeInt(1);
            Socket gracz2 = serwerSocket.accept();
            System.out.println("Dołączył 2. gracz.");
            new DataOutputStream(gracz2.getOutputStream()).writeInt(2);
            Gra gra = new Gra(gracz1, gracz2);
            Thread watek = new Thread(gra);
            watek.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Serwer serwer = new Serwer();       
    }
}
