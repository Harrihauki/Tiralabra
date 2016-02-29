/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.algoritmit;

import Polkualgoritmien_vertailu.domain.ASolmu;
import Polkualgoritmien_vertailu.domain.Solmu;
import Polkualgoritmien_vertailu.tietorakenteet.Heap;

/**
 * A*-algoritmin toimintaa mallintava luokka.
 *
 * @author lallimyl
 */
public class Astar {
    
    boolean[][] onkoNostettu;
    
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
        this.onkoNostettu = new boolean[kartta.length][kartta[0].length];
        
//        PriorityQueue<ASolmu> keko = new PriorityQueue();
        Heap keko = new Heap(kartta);
        
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
    private void etsiPolut(ASolmu[][] solmut, Heap keko, char[][] kartta, int maaliX, int maaliY) {
        
        while (!keko.isEmpty()) {
            
            Solmu u = keko.poll();
            
            if (this.onkoNostettu[u.getX()][u.getY()]) {
                continue;
            }
            
            if (u.getX() == maaliX && u.getY() == maaliY) {
                break;
            }
            
            this.onkoNostettu[u.getX()][u.getY()] = true;
            
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
    private ASolmu relax(Solmu u, ASolmu kohdesolmu, char maasto, Heap keko) {
        
        int etaisyys = 0;

        //maastoon tulee myöhemmin eri vaihtoehtoja, jotka vaikuttavat etäisyyteen
        if (maasto == '.') {
            etaisyys = 1;
        } else if (maasto == '/') {
            etaisyys = 3;
        }
        
        if (kohdesolmu.getDistance() > u.getDistance() + etaisyys) {
            ASolmu kohdesolmunDuplikaatti = new ASolmu(kohdesolmu.getX(), kohdesolmu.getY());
            kohdesolmunDuplikaatti.setDistanceToGo(kohdesolmu.getDistanceToGo());
            kohdesolmunDuplikaatti.setDistance(u.getDistance() + etaisyys);
            keko.add(kohdesolmunDuplikaatti);
            kohdesolmunDuplikaatti.setPath(u);
            
            return kohdesolmunDuplikaatti;
        }
        
        return kohdesolmu;
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
    private void tutkiVierussolmut(Solmu u, int kohdeX, int kohdeY, ASolmu[][] solmut, char[][] kartta, Heap keko, int maaliX, int maaliY) {
        
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
            solmut[kohdeX][kohdeY] = relax(u, solmut[kohdeX][kohdeY], kartta[kohdeX][kohdeY], keko);
        }
    }
}
