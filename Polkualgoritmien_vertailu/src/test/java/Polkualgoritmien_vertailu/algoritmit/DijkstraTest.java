package Polkualgoritmien_vertailu.algoritmit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Polkualgoritmien_vertailu.algoritmit.Dijkstra;
import Polkualgoritmien_vertailu.domain.Solmu;
import java.util.ArrayList;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *
 */
public class DijkstraTest {

    Dijkstra dijkstra;
    char[][] testi;

    public DijkstraTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        dijkstra = new Dijkstra();

        testi = new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '.', '#', '#', '#', '.', '.', '.', '.', '#'},
            {'#', '.', '.', '#', '#', '.', '#', '#', '.', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testaaSingleSourcenAsettamatArvot() {

        Solmu[][] solmut = dijkstra.initialiseSingleSource(testi, 1, 3);

        for (int i = 0; i < solmut.length; i++) {
            for (int j = 0; j < solmut[0].length; j++) {

                if (i == 1 && j == 3) {
                    assertEquals(0, solmut[i][j].getDistance());
                } else {
                    assertEquals(null, solmut[i][j]);
                }
            }
        }
    }

    @Test
    public void testaaSolmujenMaaraOikeaksi() {

        Solmu[][] solmut = dijkstra.initialiseSingleSource(testi, 1, 3);

        assertEquals(testi.length * testi[0].length, solmut.length * solmut[0].length);
    }

    @Test
    public void testaaRatkaisunPituus() {

        Solmu[][] solmut = dijkstra.ratkaise(testi, 3, 1, 1, 6);

        assertEquals(7, solmut[1][6].getDistance());
        
        solmut = dijkstra.ratkaise(testi, 3, 1, 2, 8);
        assertEquals(8, solmut[2][8].getDistance());
    }

    @Test
    public void testaaRatkaisunPolku() {

        Solmu[][] solmut = dijkstra.ratkaise(testi, 3, 1, 1, 6);

        Solmu[] polku = new Solmu[8];
        Solmu solmu = solmut[1][6];
        polku[0] = solmu;

        for (int i = 1; i < 8; i++) {

            solmu = solmu.getPath();
            polku[i] = solmu;
        }

        assertEquals(solmut[1][6], polku[0]);
        assertEquals(solmut[1][5], polku[1]);
        assertEquals(solmut[2][5], polku[2]);
        assertEquals(solmut[3][5], polku[3]);
        assertEquals(solmut[3][4], polku[4]);
        assertEquals(solmut[3][3], polku[5]);
        assertEquals(solmut[3][2], polku[6]);
        assertEquals(solmut[3][1], polku[7]);
    }

//    @Test
//    public void testaaLisaakoMetodiKaikkiSolmutKekoon() {
//        
//        Solmu[][] solmut = dijkstra.initialiseSingleSource(testi, 1, 3);
//        
//        PriorityQueue<Solmu> keko = new PriorityQueue();
//        
//        dijkstra.lisaaSolmutKekoon(keko, solmut);
//        
//        assertEquals(testi.length * testi[0].length, keko.size());
//    }
}
