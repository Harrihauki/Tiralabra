package Polkualgoritmien_vertailu.algoritmit;


import Polkualgoritmien_vertailu.domain.Solmu;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Luokka vastaa Dijkstran algoritmin toiminnasta ja toteutuksesta
 */
public class Dijkstra {

    /**
     *
     */
    
    public Dijkstra() {
        
    }
    
    /**
     * Metodi etsii lyhimmän reitin aloituspisteestä maalipisteeseen
     *
     * @param kartta    matriisi, joka kuvaa halutun ympäristön
     * @param aloitusX  aloituspisteen x-koordinaatti
     * @param aloitusY  aloituspisteen y-koordinaatti
     * @return  reitin pituus
     */
    public int ratkaise(char[][] kartta, int aloitusX, int aloitusY) {
        
        List<Solmu> solmut = initialiseSingleSource(kartta, aloitusX, aloitusY);
        
        PriorityQueue<Integer> keko = new PriorityQueue();
        
        return 0;
    }

    /**
     * Algoritmin alustus
     * 
     * @param kartta    matriisi, joka kuvaa halutun ympäristön
     * @param aloitusX  aloituspisteen x-koordinaatti
     * @param aloitusY  aloituspisteen y-koordinaatti
     * @return  lista kaikista kartan pisteistä alustettuna
     */
    
    
    public ArrayList<Solmu> initialiseSingleSource(char[][] kartta, int aloitusX, int aloitusY) {
        
        ArrayList<Solmu> solmut = new ArrayList();
        
        for (int i = 0; i < kartta.length; i++) {
            for (int j = 0; j < kartta[0].length; j++) {
                
                Solmu solmu = new Solmu(i, j);
                
                solmut.add(solmu);
                
                if (i == aloitusX) {
                    if (j == aloitusY) {
                        solmu.setDistance(0);
                    }
                }
            }
        }
        
        return solmut;
    }
    
    
}
