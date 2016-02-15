/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.Main;

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
     * mainissa voi testata ohjelmaa. ylläolevaa testi-taulukkoa on helppo
     * muokata, jos haluaa kokeilla erilaisia. '#' on seinä, pidä reunus niitä
     * täynnä. Ohjelman on valmiina tarkoitus täyttää ne automaattisesti, joten
     * tarkistusta taulukon ylittämiselle tai alittamiselle ei ole tehty.
     * Toistaiseksi algoritmi kulkee vain '.'-merkin kohdilla, mutta myöhemmin
     * tarkoitus tulla muitakin.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Scanner lukija = new Scanner(System.in);
        Dijkstra dijkstra = new Dijkstra();
        Astar astar = new Astar();

        System.out.println("Maalipiste on 1,6");
        System.out.println("Anna lähtöpisteen x-koordinaatti: ");
        int x = Integer.parseInt(lukija.nextLine());
        System.out.println("Anna lähtöpisteen y-koordinaatti: ");
        int y = Integer.parseInt(lukija.nextLine());

        long aikaAlussa = System.currentTimeMillis();
        Solmu[][] ratkaisu = dijkstra.ratkaise(testi, x, y, 1, 6);
        long aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
        System.out.println("polun pituus: " + ratkaisu[1][6].getDistance());
        
        aikaAlussa = System.currentTimeMillis();
        ratkaisu = astar.ratkaise(testi, x, y, 1, 6);
        aikaLopussa = System.currentTimeMillis();
        System.out.println("Operaatioon kului aikaa: " + (aikaLopussa - aikaAlussa) + "ms.");
        System.out.println("polun pituus: " + ratkaisu[1][6].getDistance());

//        while(true) {
//            System.out.println("Haluatko lyhimmän reitin pituuden lähtöpisteestä ('pituus')\nlyhimmän reitin lähtöpisteestä ('reitti')\nvai lopettaa('lopeta')?");
//            
//            String komento = lukija.nextLine();
//            
//            if (komento.equals("pituus")) {
//                System.out.println("anna halutun päätepisteen x-koordinaatti: ");
//                int x2 = Integer.parseInt(lukija.nextLine());
//                
//                System.out.println("anna halutun päätepisteen y-koordinaatti: ");
//                int y2 = Integer.parseInt(lukija.nextLine());
//                
//                System.out.println("pituus pisteeseen: " + ratkaisu[x2][y2].getDistance());
//            } else if (komento.equals("reitti")) {
//                Stack<Solmu> pino = new Stack();
//                
//                System.out.println("anna halutun päätepisteen x-koordinaatti: ");
//                int x2 = Integer.parseInt(lukija.nextLine());
//                
//                System.out.println("anna halutun päätepisteen y-koordinaatti: ");
//                int y2 = Integer.parseInt(lukija.nextLine());
//                
//                Solmu solmu = ratkaisu[x2][y2];
//                
//                System.out.println("reitti pisteeseen: ");
//                
//                while(true) {
//                    if (solmu == null) {
//                        break;
//                    }
//                    
//                    pino.add(solmu);
//                    solmu = solmu.getPath();
//                }
//                
//                while(!pino.empty()) {
//                    Solmu solmu2 = pino.pop();
//                    System.out.println(solmu2);
//                }
//            }
//        }
    }

}
