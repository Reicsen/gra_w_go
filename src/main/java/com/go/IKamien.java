package com.go;
/**
 * IKamien
 */
public interface IKamien {
    /*
     * ustawiasz odpowiedni rodzaj kamieni
     */
    public void ustawOddechy(int oddechy);
    public int podajOddechy();

    public String podajKolor();
    public void dodajKamien(String kolor);
    public void usunKamien();
    public IKamien podajKamien();
    
} 
