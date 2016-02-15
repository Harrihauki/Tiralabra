/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.tietorakenteet;

import Polkualgoritmien_vertailu.domain.Solmu;

/**
 * Luokka kuvaa keon toimintaa
 * 
 * @author lallimyl
 */
public class Heap {
    
    int heapSize;
    Solmu[] keko;
    
    public Heap(char[][] kartta) {
        
        this.heapSize = -1;
        this.keko = new Solmu[kartta.length * kartta[0].length * 2 / 3];
    }
    
    /**
     * Palauttaa keon päällimmäisen alkion
     * 
     * @return Solmu päällimmäinen
     */
    public Solmu heapMin() {
        return this.keko[0];
    }
    
    /**
     * "Ottaa" keosta päälimmäisen alkion
     * 
     * @return Solmu päällimmäinen
     */
    public Solmu poll() {
        Solmu min = this.keko[0];
        this.keko[0] = this.keko[this.heapSize];
        this.heapSize--;
        heapify(0);
        
        return min;
    }
    
    /**
     * Palauttaa true, jos keko on tyhjä, muuten false
     * 
     * @return  boolean
     */
    public boolean isEmpty() {
        
        if (this.heapSize >= 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Metodi lisää uuden solmun kekoon oikealle paikalleen. Metodi huomioi jo vedettyjen
     * solmujen duplikaatit ja hyppii niiden yli.
     * 
     * @param solmu 
     */
    public void add(Solmu solmu) {
        this.heapSize++;
        int i = this.heapSize;
        
        if (this.heapSize < 1) {
            this.keko[0] = solmu;
            return;
        }
        
        while ((i > 0 && solmu.compareTo(this.keko[parent(i)]) <= 0) || this.keko[parent(i)].getPulled()) {
            this.keko[i] = this.keko[parent(i)];
            i = parent(i);
        }
        
        this.keko[i] = solmu;
    }
    
    /**
     * Palauttaa indeksissä i olevan solmun vanhemman indeksin
     * 
     * @param i
     * @return indeksin i vanhemman indeksi
     */
    public int parent(int i) {
        return i/2;
    }
    
    /**
     * Palauttaa indeksissä i olevan solmun vasemman lapsen indeksin
     * 
     * @param i
     * @return  indeksin i vasemman lapsen indeksi
     */
    public int left(int i) {
        return 2*i;
    }
    
    /**
     * Palauttaa indeksissä i olevan solmun oikean lapsen indeksin
     * 
     * @param i
     * @return  indeksin i oikean lapsen indeksi
     */
    public int right(int i) {
        return 2*i + 1;
    }
    
    /**
     * Pidetään huolta, että kekoehto säilyy indeksistä i alaspäin. Metodi huomioi
     * jo vedettyjen solmujen duplikaatit ja hyppii niiden yli.
     * 
     * @param i 
     */
    private void heapify(int i) {
        
        int l = this.left(i);
        int r = this.right(i);
        
        if (r <= this.heapSize) {
            int smallest;
            
            if (keko[l].compareTo(keko[r]) < 0) {
                smallest = l;
            } else {
                smallest = r;
            }
                        
            if (keko[i].compareTo(keko[smallest]) >= 0 || keko[smallest].getPulled()) {
                Solmu apu = keko[i];
                keko[i] = keko[smallest];
                keko[smallest] = apu;
                heapify(smallest);
            }
        } else if (l == this.heapSize) {
            if (keko[i].compareTo(keko[l]) > 0) {
                Solmu apu = keko[i];
                keko[i] = keko[l];
                keko[l] = apu;
            }
        }
    }
}
