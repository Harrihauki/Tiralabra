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
import java.util.Scanner;
import java.util.Stack;

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
                + "pienen testisokkelon avulla ja muut komennot suorittavat suorituskykyvertailua antamillasi arvoilla.");

        komento = lukija.nextLine();

        while (!komento.equals("x")) {

            if (komento.equals("kuva")) {
                kuva(generaattori, dijkstra, astar);
            } else {
                System.out.println("Anna sokkelon leveys: ");
                int leveys = Integer.parseInt(lukija.nextLine());
                System.out.println("Anna sokkelon korkeus: ");
                int korkeus = Integer.parseInt(lukija.nextLine());

                System.out.println("Älä anna lähtö- tai maalipisteitä aivan sokkelon reunoilta,\n"
                        + "sillä algoritmit voivat tällöin hypätä ulos taulukoista.\n");

                System.out.println("Anna maalipisteen x-koordinaatti: ");
                int maaliX = Integer.parseInt(lukija.nextLine());
                System.out.println("Anna maalipisteen y-koordinaatti: ");
                int maaliY = Integer.parseInt(lukija.nextLine());

                System.out.println("Anna lähtöpisteen x-koordinaatti: ");
                int x = Integer.parseInt(lukija.nextLine());
                System.out.println("Anna lähtöpisteen y-koordinaatti: ");
                int y = Integer.parseInt(lukija.nextLine());

                char[][] sokkelo = generaattori.luoKartta(leveys, korkeus, x, y, maaliX, maaliY);

                long kokonaisaika = 0;
                long aikaAlussa = 0;
                long aikaLopussa = 0;
                Solmu[][] ratkaisu = dijkstra.ratkaise(sokkelo, x, y, maaliX, maaliY);

                for (int i = 0; i < 100; i++) {
                    aikaAlussa = System.currentTimeMillis();
                    ratkaisu = dijkstra.ratkaise(sokkelo, x, y, maaliX, maaliY);
                    aikaLopussa = System.currentTimeMillis();
                    kokonaisaika += aikaLopussa - aikaAlussa;
                }

                System.out.println("Dijkstraan kului aikaa keskimäärin: " + (1.0 * kokonaisaika / 100) + "ms.");
                System.out.println("polun pituus: " + ratkaisu[maaliX][maaliY].getDistance());

                kokonaisaika = 0;

                for (int i = 0; i < 100; i++) {
                    aikaAlussa = System.currentTimeMillis();
                    ratkaisu = astar.ratkaise(sokkelo, x, y, maaliX, maaliY);
                    aikaLopussa = System.currentTimeMillis();
                    kokonaisaika += aikaLopussa - aikaAlussa;
                }

                System.out.println("Astariin kului keskimäärin aikaa: " + (1.0 * kokonaisaika / 100) + "ms.");
                System.out.println("polun pituus: " + ratkaisu[maaliX][maaliY].getDistance());
            }

            System.out.println("Anna komento. 'x' lopettaa, 'kuva' havainnollistaa algoritmien etenemisen eroja\n"
                    + "pienen testisokkelon avulla ja muut komennot suorittavat suorituskykyvertailua antamillasi arvoilla.");

            komento = lukija.nextLine();
        }
    }

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

}
