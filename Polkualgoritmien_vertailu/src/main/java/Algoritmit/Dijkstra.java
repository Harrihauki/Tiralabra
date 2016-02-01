package Algoritmit;


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
    
    private PriorityQueue<Integer> keko;
    
    /*
     * Konstruktori alustaa keon, jota käytetään algoritmin etenemisjärjestyksen
     * määrittämisessä.
     */
    public Dijkstra() {
        
        this.keko = new PriorityQueue();
    }
    
    public int ratkaise(char[][] kartta, int aloitusX, int aloitusY) {
        
        int[][] distance = initialiseSingleSource(kartta, aloitusX, aloitusY);
        
        return 0;
    }

    private int[][] initialiseSingleSource(char[][] kartta, int aloitusX, int aloitusY) {
        
        
    }
    
    
}
