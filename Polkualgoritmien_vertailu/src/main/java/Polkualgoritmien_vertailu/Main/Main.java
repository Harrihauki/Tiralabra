/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.Main;

import Polkualgoritmien_vertailu.Sokkelogeneraattori.Sokkelogeneraattori;
import Polkualgoritmien_vertailu.algoritmit.Astar;
import Polkualgoritmien_vertailu.algoritmit.Dijkstra;
import Polkualgoritmien_vertailu.domain.Solmu;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lallimyl
 */
public class Main {

    static char[][] testi = new char[][]{
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '.', '#', '#', '#', '.', '.', '.', '.', '#'},
        {'#', '.', '.', '#', '#', '.', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};

    /**
     * mainissa voi testata ohjelmaa.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner lukija = new Scanner(System.in);
        Dijkstra dijkstra = new Dijkstra();
        Astar astar = new Astar();
        Sokkelogeneraattori generaattori = new Sokkelogeneraattori();
        String komento = "";

        System.out.println("Anna komento. 'x' lopettaa, 'kuva' havainnollistaa algoritmien etenemisen eroja\n"
                + "pienen testisokkelon avulla, 'pituustesti' vertaa, saavatko algoritmit saman tuloksen polun\n"
                + "pituudelle, eli toimivatko algoritmit ja muut komennot suorittavat suorituskykyvertailua \n"
                + "antamillasi arvoilla.\n");

        komento = lukija.nextLine();

        while (!komento.equals("x")) {

            if (komento.equals("kuva")) {
                kuva(generaattori, dijkstra, astar);
            } else if (komento.equals("pituustesti")) {
                pituus(generaattori, dijkstra, astar);
            } else {
                System.out.println("Anna sokkelon leveys: ");
                int leveys = Integer.parseInt(lukija.nextLine());
                System.out.println("Anna sokkelon korkeus: ");
                int korkeus = Integer.parseInt(lukija.nextLine());

                System.out.println("\nÄlä anna lähtö- tai maalipisteitä aivan sokkelon reunoilta,\n"
                        + "sillä algoritmit voivat tällöin hypätä ulos taulukoista.\n\n");

                System.out.println("Anna maalipisteen x-koordinaatti: ");
                int maaliX = Integer.parseInt(lukija.nextLine());
                System.out.println("Anna maalipisteen y-koordinaatti: ");
                int maaliY = Integer.parseInt(lukija.nextLine());

                System.out.println("Anna lähtöpisteen x-koordinaatti: ");
                int x = Integer.parseInt(lukija.nextLine());
                System.out.println("Anna lähtöpisteen y-koordinaatti: ");
                int y = Integer.parseInt(lukija.nextLine());

                System.out.println("Odota...\n");
                
                char[][] sokkelo = generaattori.luoKartta(leveys, korkeus, x, y, maaliX, maaliY);

                long dijkstranKokonaisaika = 0;
                long astarinKokonaisaika = 0;
                long aikaAlussa = 0;
                long aikaLopussa = 0;
                Solmu[][] ratkaisu = dijkstra.ratkaise(sokkelo, x, y, maaliX, maaliY);

                for (int i = 0; i < 100; i++) {
                    sokkelo = generaattori.luoKartta(leveys, korkeus, x, y, maaliX, maaliY);

                    aikaAlussa = System.currentTimeMillis();
                    ratkaisu = dijkstra.ratkaise(sokkelo, x, y, maaliX, maaliY);
                    aikaLopussa = System.currentTimeMillis();
                    dijkstranKokonaisaika += aikaLopussa - aikaAlussa;

                    aikaAlussa = System.currentTimeMillis();
                    ratkaisu = astar.ratkaise(sokkelo, x, y, maaliX, maaliY);
                    aikaLopussa = System.currentTimeMillis();
                    astarinKokonaisaika += aikaLopussa - aikaAlussa;
                }

                System.out.println("Dijkstraan kului aikaa keskimäärin: " + (1.0 * dijkstranKokonaisaika / 100) + "ms.");

                System.out.println("Astariin kului keskimäärin aikaa: " + (1.0 * astarinKokonaisaika / 100) + "ms.");
            }

            System.out.println("Anna komento. 'x' lopettaa, 'kuva' havainnollistaa algoritmien etenemisen eroja\n"
                + "pienen testisokkelon avulla, 'pituustesti' vertaa, saavatko algoritmit saman tuloksen polun\n"
                + "pituudelle, eli toimivatko algoritmit ja muut komennot suorittavat suorituskykyvertailua \n"
                + "antamillasi arvoilla.\n");

            komento = lukija.nextLine();
        }
    }

    /**
     * Tulostaa 25*25-kokoisen taulukon, jossa on havainnollisesti
     * ascii-merkeillä mihin suuntaan algoritmit ovat lähteneet tutkimaan.
     *
     * @param generaattori
     * @param dijkstra
     * @param astar
     */
    private static void kuva(Sokkelogeneraattori generaattori, Dijkstra dijkstra, Astar astar) {

        char[][] sokkelo = generaattori.luoKartta(25, 25, 15, 15, 3, 3);

        Solmu[][] solmut = dijkstra.ratkaise(sokkelo, 15, 15, 3, 3);

        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut[0].length; j++) {
                if (solmut[i][j] != null) {
                    if (solmut[i][j].getDistance() < 25) {
                        System.out.print((char) (solmut[i][j].getDistance() + 'a'));
                    }
                } else {
                    System.out.print(sokkelo[i][j]);
                }
            }
            System.out.print("\n");
        }

        System.out.println("");

        solmut = astar.ratkaise(sokkelo, 15, 15, 3, 3);

        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut[0].length; j++) {
                if (solmut[i][j] != null) {
                    if (solmut[i][j].getDistance() < 50) {
                        System.out.print((char) (solmut[i][j].getDistance() + 'a'));
                    }
                } else {
                    System.out.print(sokkelo[i][j]);
                }
            }
            System.out.print("\n");
        }

        System.out.println("");
    }

    /**
     * Metodi luo sata satunnaista 500*500-kokoista sokkealoa, jotka se ratkaisee
     * molemmilla algoritmeilla. Jos algoritmien ilmoittamat polun pituudet 
     * eroavat, voi tehdä tulkinnan, että ainakaan toinen algoritmeista ei toimi.
     * 
     * @param generaattori sokkelogeneraattori sokkeloiden luomiseen.
     * @param dijkstra Dajkstra-algoritmi
     * @param astar A*-algoritmi
     */
    private static void pituus(Sokkelogeneraattori generaattori, Dijkstra dijkstra, Astar astar) {

        Random arpoja = new Random();
        
        System.out.println("Metodi tulostaa true, jos algoritmit saavat saman\n"
                + "polun pituuden ratkaisuksi sadalla eri 500*500-kokoisella sokkelolla,\n"
                + "joiden lähtö- ja maalipisteet arvotaan.\n");
        
        for (int i = 0; i < 100; i++) {
            int x = arpoja.nextInt(498)+1;
            int y = arpoja.nextInt(498)+1;
            int maaliX = arpoja.nextInt(498)+1;
            int maaliY = arpoja.nextInt(498)+1;
            char [][] sokkelo = generaattori.luoKartta(500, 500, x, y, maaliX, maaliY);

            Solmu[][] dijkRatkaisu = dijkstra.ratkaise(sokkelo, x, y, maaliX, maaliY);

            Solmu[][] astarRatkaisu = astar.ratkaise(sokkelo, x, y, maaliX, maaliY);

            if (dijkRatkaisu[maaliX][maaliY] == null) {
                continue;
            } else if (dijkRatkaisu[maaliX][maaliY].getDistance() != astarRatkaisu[maaliX][maaliY].getDistance()) {
                System.out.println("false\n");
            }
        }
        
        System.out.println("true\n");
    }
}
