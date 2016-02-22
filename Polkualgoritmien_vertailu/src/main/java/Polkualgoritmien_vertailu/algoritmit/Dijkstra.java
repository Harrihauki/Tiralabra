package Polkualgoritmien_vertailu.algoritmit;

import Polkualgoritmien_vertailu.domain.Solmu;
import Polkualgoritmien_vertailu.tietorakenteet.Heap;
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

    boolean[][] onkoNostettu;
    
    /**
     *
     */
    public Dijkstra() {
        
    }

    /**
     * Metodi etsii lyhimmÃ¤n reitin aloituspisteestÃ¤ maalipisteeseen
     *
     * @param kartta matriisi, joka kuvaa halutun ympÃ¤ristÃ¶n
     * @param aloitusX aloituspisteen x-koordinaatti
     * @param aloitusY aloituspisteen y-koordinaatti
     * @return reitin pituus
     */
    public Solmu[][] ratkaise(char[][] kartta, int aloitusX, int aloitusY, int maaliX, int maaliY) {

        Solmu[][] solmut = initialiseSingleSource(kartta, aloitusX, aloitusY);
        this.onkoNostettu = new boolean[kartta.length][kartta[0].length];

//        PriorityQueue<Solmu> keko = new PriorityQueue();
        Heap keko = new Heap(kartta);

        keko.add(solmut[aloitusX][aloitusY]);

        etsiPolut(solmut, keko, kartta, maaliX, maaliY);

        return solmut;
    }

    /**
     * Algoritmin alustus. Vain aloitussolmu tarvitsee alustaa taulukkoon, muut
     * lasketaan ja luodaan algoritmin suoritusaikana.
     *
     * @param kartta matriisi, joka kuvaa halutun ympÃ¤ristÃ¶n
     * @param aloitusX aloituspisteen x-koordinaatti
     * @param aloitusY aloituspisteen y-koordinaatti
     * @return lista kaikista kartan pisteistÃ¤ alustettuna
     */
    public Solmu[][] initialiseSingleSource(char[][] kartta, int aloitusX, int aloitusY) {

        Solmu[][] solmut = new Solmu[kartta.length][kartta[0].length];

        Solmu solmu = new Solmu(aloitusX, aloitusY);
        solmu.setDistance(0);
        solmut[aloitusX][aloitusY] = solmu;

        return solmut;
    }

    /**
     * Metodi lisÃ¤Ã¤ solmut kekoon
     *
     * @param keko Keko, johon solmut lisÃ¤tÃ¤Ã¤n
     * @param solmut Lista solmuista
     */
//    public void lisaaSolmutKekoon(PriorityQueue<Solmu> keko, List<Solmu> solmut) {
//
//        for (Solmu solmu : solmut) {
//            keko.add(solmu);
//        }
//    }

    /**
     * Metodi etsii lyhimmÃ¤n polun lÃ¤htÃ¶solmusta muihin solmuihin
     *
     * @param solmut Taulukko, johon algoritmin edetessÃ¤ merkitÃ¤Ã¤n solmut
     * @param keko Keon avulla valvotaan, ettÃ¤ aina siirrytÃ¤Ã¤n parhaalta
     * vaikuttavaan solmuun
     * @param kartta Matriisi, joka kuvaa halutun ympÃ¤ristÃ¶n
     */
    private void etsiPolut(Solmu[][] solmut, Heap keko, char[][] kartta, int maaliX, int maaliY) {

        while (!keko.isEmpty()) {

            Solmu u = keko.poll();
            
            if (this.onkoNostettu[u.getX()][u.getY()]) {
                continue;
            }
            
            if (u.getX() == maaliX && u.getY() == maaliY) {
                break;
            }
            
            this.onkoNostettu[u.getX()][u.getY()] = true;

            tutkiVierussolmut(u, u.getX() + 1, u.getY(), solmut, kartta, keko);
            tutkiVierussolmut(u, u.getX() - 1, u.getY(), solmut, kartta, keko);
            tutkiVierussolmut(u, u.getX(), u.getY() + 1, solmut, kartta, keko);
            tutkiVierussolmut(u, u.getX(), u.getY() - 1, solmut, kartta, keko);

        }
    }

    /**
     * Metodi tutkii, onko solmusta sen vierussolmuun kulkeva reitti lyhin tois-
     * taiseksi tunnettu reitti.
     *
     * @param u KÃ¤sittelyssÃ¤ oleva solmu
     * @param kohdesolmu u:n vierussolmu
     * @param maasto Merkki, jolla kuvataan maaston vaikeutta u:n ja kohdesolmun
     * vÃ¤lillÃ¤
     * @param keko Keon avulla valvotaan, ettÃ¤ aina siirrytÃ¤Ã¤n parhaalta
     * vaikuttavaan solmuun
     */
    private void relax(Solmu u, Solmu kohdesolmu, char maasto, Heap keko) {

        int etaisyys = 0;

        //maastoon tulee myÃ¶hemmin eri vaihtoehtoja, jotka vaikuttavat etÃ¤isyyteen
        if (maasto == '.') {
            etaisyys = 1;
        } else if (maasto == '/') {
            etaisyys = 3;
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
     * @param u KÃ¤sittelyssÃ¤ olevaa pistettÃ¤ vastaava solmu
     * @param kohdeX    kohdepisteen x-koordinaatti
     * @param kohdeY    kohdepisteen y-koordinaatti
     * @param solmut    matriisi, johon tallenetaan kutankin koordinaattia vastaava solmu
     * @param kartta    Matriisi, joka kuvaa halutun ympÃ¤ristÃ¶n
     * @param keko      Sama keko kuin kaikkialla muuallakin
     */
    private void tutkiVierussolmut(Solmu u, int kohdeX, int kohdeY, Solmu[][] solmut, char[][] kartta, Heap keko) {

        if (solmut[kohdeX][kohdeY] == null) {
            if (kartta[kohdeX][kohdeY] != '#') {
                solmut[kohdeX][kohdeY] = new Solmu(kohdeX, kohdeY);
            }
        }

        if (solmut[kohdeX][kohdeY] != null) {
            relax(u, solmut[kohdeX][kohdeY], kartta[kohdeX][kohdeY], keko);
        }
    }

}
