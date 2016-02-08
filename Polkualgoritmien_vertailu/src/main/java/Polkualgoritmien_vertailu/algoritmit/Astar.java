/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.algoritmit;

import Polkualgoritmien_vertailu.domain.ASolmu;
import java.util.PriorityQueue;

/**
 * A*-algoritmin toimintaa mallintava luokka.
 *
 * @author lallimyl
 */
public class Astar {
    
    public Astar() {
        
    }

    /**
     * Metodi etsii lyhimmän reitin aloituspisteestä maalipisteeseen
     *
     * @param kartta matriisi, joka kuvaa halutun ympäristön
     * @param aloitusX aloituspisteen x-koordinaatti
     * @param aloitusY aloituspisteen y-koordinaatti
     * @param maaliX maalisolmun x-koordinaatti
     * @param maaliY maalisolmun y-koordinaatti
     * @return reitin pituus
     */
    public ASolmu[][] ratkaise(char[][] kartta, int aloitusX, int aloitusY, int maaliX, int maaliY) {
        
        ASolmu[][] solmut = initialiseSingleSource(kartta, aloitusX, aloitusY);
        
        PriorityQueue<ASolmu> keko = new PriorityQueue();
        
        keko.add(solmut[aloitusX][aloitusY]);
        
        etsiPolut(solmut, keko, kartta, maaliX, maaliY);
        
        return solmut;
    }

    /**
     * Algoritmin alustus. Vain aloitussolmu tarvitsee alustaa taulukkoon, muut
     * lasketaan ja luodaan algoritmin suoritusaikana.
     *
     * @param kartta matriisi, joka kuvaa halutun ympäristön
     * @param aloitusX aloituspisteen x-koordinaatti
     * @param aloitusY aloituspisteen y-koordinaatti
     * @return lista kaikista kartan pisteistä alustettuna
     */
    public ASolmu[][] initialiseSingleSource(char[][] kartta, int aloitusX, int aloitusY) {
        
        ASolmu[][] solmut = new ASolmu[kartta.length][kartta[0].length];
        
        ASolmu solmu = new ASolmu(aloitusX, aloitusY);
        solmu.setDistance(0);
        solmut[aloitusX][aloitusY] = solmu;
        
        return solmut;
    }

    /**
     * Metodi etsii lyhimmän polun lähtösolmusta muihin solmuihin
     *
     * @param solmut Taulukko, johon algoritmin edetessä merkitään solmut
     * @param keko Keon avulla valvotaan, että aina siirrytään parhaalta
     * vaikuttavaan solmuun
     * @param kartta Matriisi, joka kuvaa halutun ympäristön
     */
    private void etsiPolut(ASolmu[][] solmut, PriorityQueue<ASolmu> keko, char[][] kartta, int maaliX, int maaliY) {
        
        while (!keko.isEmpty()) {
            
            ASolmu u = keko.poll();
            
            if (u.getX() == maaliX && u.getY() == maaliY) {
                break;
            }
            
            tutkiVierussolmut(u, u.getX() + 1, u.getY(), solmut, kartta, keko, maaliX, maaliY);
            tutkiVierussolmut(u, u.getX() - 1, u.getY(), solmut, kartta, keko, maaliX, maaliY);
            tutkiVierussolmut(u, u.getX(), u.getY() + 1, solmut, kartta, keko, maaliX, maaliY);
            tutkiVierussolmut(u, u.getX(), u.getY() - 1, solmut, kartta, keko, maaliX, maaliY);
            
        }
    }

    /**
     * Metodi tutkii, onko solmusta sen vierussolmuun kulkeva reitti lyhin tois-
     * taiseksi tunnettu reitti.
     *
     * @param u Käsittelyssä oleva solmu
     * @param kohdesolmu u:n vierussolmu
     * @param maasto Merkki, jolla kuvataan maaston vaikeutta u:n ja kohdesolmun
     * välillä
     * @param keko Keon avulla valvotaan, että aina siirrytään parhaalta
     * vaikuttavaan solmuun
     */
    private void relax(ASolmu u, ASolmu kohdesolmu, char maasto, PriorityQueue<ASolmu> keko) {
        
        int etaisyys = 0;

        //maastoon tulee myöhemmin eri vaihtoehtoja, jotka vaikuttavat etäisyyteen
        if (maasto == '.') {
            etaisyys = 1;
        }
        
        if (kohdesolmu.getDistance() > u.getDistance() + etaisyys) {
            kohdesolmu.setDistance(u.getDistance() + etaisyys);
            keko.add(kohdesolmu);
            kohdesolmu.setPath(u);
        }
    }

    /**
     * Metodi tutkii pisteen mahdollista vierussolmua
     *
     * @param u Käsittelyssä olevaa pistettä vastaava solmu
     * @param kohdeX kohdepisteen x-koordinaatti
     * @param kohdeY kohdepisteen y-koordinaatti
     * @param solmut matriisi, johon tallenetaan kutankin koordinaattia vastaava
     * solmu
     * @param kartta Matriisi, joka kuvaa halutun ympäristön
     * @param keko Sama keko kuin kaikkialla muuallakin
     */
    private void tutkiVierussolmut(ASolmu u, int kohdeX, int kohdeY, ASolmu[][] solmut, char[][] kartta, PriorityQueue<ASolmu> keko, int maaliX, int maaliY) {
        
        if (solmut[kohdeX][kohdeY] == null) {
            if (kartta[kohdeX][kohdeY] != '#') {
                ASolmu solmu = new ASolmu(kohdeX, kohdeY);
                solmut[kohdeX][kohdeY] = solmu;
                
                if (kohdeX < maaliX) {
                    solmu.setDistanceToGo(maaliX - kohdeX);
                } else {
                    solmu.setDistanceToGo(kohdeX - maaliX);
                }
                
                if (kohdeY < maaliY) {
                    solmu.setDistanceToGo(solmu.getDistanceToGo() + maaliY - kohdeY);
                } else {
                    solmu.setDistanceToGo(solmu.getDistanceToGo() + kohdeY - maaliY);
                }
            }
        }
        
        if (solmut[kohdeX][kohdeY] != null) {
            relax(u, solmut[kohdeX][kohdeY], kartta[kohdeX][kohdeY], keko);
        }
    }
}
