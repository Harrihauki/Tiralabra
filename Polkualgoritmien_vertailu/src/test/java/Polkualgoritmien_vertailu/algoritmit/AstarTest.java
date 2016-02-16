/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.algoritmit;

import Polkualgoritmien_vertailu.domain.ASolmu;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lallimyl
 */
public class AstarTest {
    
    char[][] testi;
    Astar astar;
    
    public AstarTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        astar = new Astar();
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

        ASolmu[][] solmut = astar.initialiseSingleSource(testi, 1, 3);

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

        ASolmu[][] solmut = astar.initialiseSingleSource(testi, 1, 3);

        assertEquals(testi.length * testi[0].length, solmut.length * solmut[0].length);
    }
    
    @Test
    public void testaaRatkaisunPituus() {

        ASolmu[][] solmut = astar.ratkaise(testi, 3, 1, 1, 6);

        assertEquals(7, solmut[1][6].getDistance());
        
        solmut = astar.ratkaise(testi, 3, 1, 2, 8);
        
        assertEquals(8, solmut[2][8].getDistance());
    }
    
    @Test
    public void testaaRatkaisunPolku() {

        ASolmu[][] solmut = astar.ratkaise(testi, 3, 1, 1, 6);

        ASolmu[] polku = new ASolmu[8];
        ASolmu solmu = solmut[1][6];
        polku[0] = solmu;

        for (int i = 1; i < 8; i++) {

            solmu = (ASolmu) solmu.getPath();
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
}
