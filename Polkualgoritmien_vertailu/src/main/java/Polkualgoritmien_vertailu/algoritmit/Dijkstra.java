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

    /**
     *
     */
    public Dijkstra() {

    }

    /**
     * Metodi etsii lyhimmän reitin aloituspisteestä maalipisteeseen
     *
     * @param kartta matriisi, joka kuvaa halutun ympäristön
     * @param aloitusX aloituspisteen x-koordinaatti
     * @param aloitusY aloituspisteen y-koordinaatti
     * @return reitin pituus
     */
    public Solmu[][] ratkaise(char[][] kartta, int aloitusX, int aloitusY, int maaliX, int maaliY) {

        Solmu[][] solmut = initialiseSingleSource(kartta, aloitusX, aloitusY);

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
     * @param kartta matriisi, joka kuvaa halutun ympäristön
     * @param aloitusX aloituspisteen x-koordinaatti
     * @param aloitusY aloituspisteen y-koordinaatti
     * @return lista kaikista kartan pisteistä alustettuna
     */
    public Solmu[][] initialiseSingleSource(char[][] kartta, int aloitusX, int aloitusY) {

        Solmu[][] solmut = new Solmu[kartta.length][kartta[0].length];

        Solmu solmu = new Solmu(aloitusX, aloitusY);
        solmu.setDistance(0);
        solmut[aloitusX][aloitusY] = solmu;

        return solmut;
    }

    /**
     * Metodi lisää solmut kekoon
     *
     * @param keko Keko, johon solmut lisätään
     * @param solmut Lista solmuista
     */
//    public void lisaaSolmutKekoon(PriorityQueue<Solmu> keko, List<Solmu> solmut) {
//
//        for (Solmu solmu : solmut) {
//            keko.add(solmu);
//        }
//    }

    /**
     * Metodi etsii lyhimmän polun lähtösolmusta muihin solmuihin
     *
     * @param solmut Taulukko, johon algoritmin edetessä merkitään solmut
     * @param keko Keon avulla valvotaan, että aina siirrytään parhaalta
     * vaikuttavaan solmuun
     * @param kartta Matriisi, joka kuvaa halutun ympäristön
     */
    private void etsiPolut(Solmu[][] solmut, Heap keko, char[][] kartta, int maaliX, int maaliY) {

        while (!keko.isEmpty()) {

            Solmu u = keko.poll();
            
            if (u.getPulled()) {
                continue;
            }
            
            if (u.getX() == maaliX && u.getY() == maaliY) {
                break;
            }
            
            u.pulled();

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
     * @param u Käsittelyssä oleva solmu
     * @param kohdesolmu u:n vierussolmu
     * @param maasto Merkki, jolla kuvataan maaston vaikeutta u:n ja kohdesolmun
     * välillä
     * @param keko Keon avulla valvotaan, että aina siirrytään parhaalta
     * vaikuttavaan solmuun
     */
    private void relax(Solmu u, Solmu kohdesolmu, char maasto, Heap keko) {

        int etaisyys = 0;

        //maastoon tulee myöhemmin eri vaihtoehtoja, jotka vaikuttavat etäisyyteen
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
     * @param u Käsittelyssä olevaa pistettä vastaava solmu
     * @param kohdeX    kohdepisteen x-koordinaatti
     * @param kohdeY    kohdepisteen y-koordinaatti
     * @param solmut    matriisi, johon tallenetaan kutankin koordinaattia vastaava solmu
     * @param kartta    Matriisi, joka kuvaa halutun ympäristön
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
