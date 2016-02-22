/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.Sokkelogeneraattori;

import java.util.Random;

/**
 * Luokka luo satunnaisen sokkelon, jota käytetään algoritmien vertailussa
 * 
 * @author Myllyaho
 */
public class Sokkelogeneraattori {
    
    Random arpoja;
    
    public Sokkelogeneraattori() {
        this.arpoja = new Random();
    }
    
    public char[][] luoKartta(int x, int y, int lahtoX, int lahtoY, int maaliX, int maaliY) {
        
        char[][] kartta = new char[x][y];
        
        for (int i = 1; i < x-1; i++) {
            for (int j = 1; j < y-1; j++) {
                int arpa = this.arpoja.nextInt(10);
                
                if (arpa < 1) {
                    kartta[i][j] = '#';
                } else if (arpa < 3) {
                    kartta[i][j] = '/';
                } else {
                    kartta[i][j] = '.';
                }
            }
        }
        
        luoSeinat(kartta);
        
        kartta[lahtoX][lahtoY] = '.';
        kartta[maaliX][maaliY] = '.';
        
        return kartta;
    }

    private void luoSeinat(char[][] kartta) {
        
        for (int i = 0; i < kartta.length; i++) {
            kartta[i][0] = '#';
            kartta[i][kartta[0].length-1] = '#';
        }
        
        for (int i = 0; i < kartta[0].length; i++) {
            kartta[0][i] = '#';
            kartta[kartta.length-1][i] = '#';
        }
        
        
    }
}
