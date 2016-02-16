/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.domain;

/**
 * A*-algoritmissa käytettävä solmu. Vertaa etäisyyksiä eri tavalla suhteessa
 * "tavalliseen" solmuun.
 *
 * @author Myllyaho
 */
public class ASolmu extends Solmu {

    int distanceToGo;
//    ASolmu path;

    public ASolmu(int x, int y) {
        super(x, y);
        this.distanceToGo = Integer.MAX_VALUE;
//        this.path = null;
    }

    public void setDistanceToGo(int distanceToGo) {
        this.distanceToGo = distanceToGo;
    }

    public int getDistanceToGo() {
        return this.distanceToGo;
    }

    @Override
    public int compareTo(Object o) {

        if (o.getClass() == this.getClass()) {

            ASolmu solmu = (ASolmu) o;
            Integer etaisyys = super.getDistance() + this.distanceToGo;
            return etaisyys.compareTo(solmu.getDistance() + solmu.getDistanceToGo());
        }

        return 0;
    }
    
//    public void setPath(ASolmu path) {
//        this.path = path;
//    }
    
//    @Override
//    public ASolmu getPath() {
//        return this.path;
//    }
}
