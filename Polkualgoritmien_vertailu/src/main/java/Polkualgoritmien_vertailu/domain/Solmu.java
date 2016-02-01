/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.domain;

/**
 * Luokka kuvaa kartan pisteitä ja pitää yllä sen etäisyyttä lähtöpisteestä, sekä
 * edellisestä solmusta reitillä
 *
 * @author lallimyl
 */
public class Solmu implements Comparable {
    
    int x;
    int y;
    int distance;
    Solmu path;
    
    /**
     *
     * @param x
     * @param y
     */
    public Solmu(int x, int y) {
        this.x = x;
        this.y = y;
        this.distance = Integer.MAX_VALUE;
        this.path = null;
    }
    
    /**
     *
     * @param distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    /**
     *
     * @return
     */
    public int getX() {
        return this.x;
    }
    
    /**
     *
     * @return
     */
    public int getY() {
        return this.y;
    }
    
    /**
     *
     * @return
     */
    public int getDistance() {
        return this.distance;
    }

    @Override
    public int compareTo(Object o) {
        
        
    }
}
