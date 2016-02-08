/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.domain;

/**
 * A*-algoritmissa käytettävä solmu
 *
 * @author Myllyaho
 */
public class ASolmu extends Solmu {
    
    int distanceToGo;

    public ASolmu(int x, int y) {
        super(x, y);
        this.distanceToGo = Integer.MAX_VALUE;
    }
    
}
