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
            {'#', '#', '#', '#', '#', '.', '#', '#', '#', '#'},
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
        
        ArrayList<Solmu> solmut = dijkstra.initialiseSingleSource(testi, 1, 3);
        
        for (Solmu solmu : solmut) {
            
            if (solmu.getX() == 1) {
                if (solmu.getY() == 3) {
                    assertFalse(solmu.getDistance() != 0);
                } else {
                    assertFalse(solmu.getDistance() != Integer.MAX_VALUE);
                }
            } else {
                assertFalse(solmu.getDistance() != Integer.MAX_VALUE);
            }
        }
    }
    
    @Test
    public void testaaSolmujenMaaraOikeaksi() {
        
        ArrayList<Solmu> solmut = dijkstra.initialiseSingleSource(testi, 1, 3);
        
        assertEquals(testi.length * testi[0].length, solmut.size());
    }
    
    @Test
    public void testaaLisaakoMetodiKaikkiSolmutKekoon() {
        
        ArrayList<Solmu> solmut = dijkstra.initialiseSingleSource(testi, 1, 3);
        
        PriorityQueue<Solmu> keko = new PriorityQueue();
        
        dijkstra.lisaaSolmutKekoon(keko, solmut);
        
        assertEquals(testi.length * testi[0].length, keko.size());
    }
    
    @Test
    public void testaaTulevatkoSolmutKekoonOikeassaJarjestyksessa() {
        
        ArrayList<Solmu> solmut = dijkstra.initialiseSingleSource(testi, 1, 3);
        
        PriorityQueue<Solmu> keko = new PriorityQueue();
        
        dijkstra.lisaaSolmutKekoon(keko, solmut);
        
        Solmu solmu = keko.poll();
        
        assertEquals(1, solmu.getX());
        assertEquals(3, solmu.getY());
    }
}
