/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.tietorakenteet;

import Polkualgoritmien_vertailu.domain.Solmu;
import java.util.Random;
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
public class HeapTest {
    
    Heap keko;
    char[][] testi;
    Solmu solmu1;
    Solmu solmu2;
    
    public HeapTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        testi = new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '.', '#', '#', '#', '.', '.', '.', '.', '#'},
            {'#', '.', '.', '#', '#', '.', '#', '#', '.', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
        
        keko = new Heap(testi);
        solmu1 = new Solmu(1,3);
        solmu2 = new Solmu(2,3);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addToimii() {
        
        solmu1.setDistance(5);
        solmu2.setDistance(3);
        
        keko.add(solmu1);
        keko.add(solmu2);
        
        assertEquals(solmu2, keko.poll());
    }
    
    @Test
    public void addToimiiUseammillaSyotteilla() {
        
        Random arpoja = new Random();
        
        for (int i = 0; i < 25; i++) {
            Solmu solmu = new Solmu(1,1);
            solmu.setDistance(arpoja.nextInt(100));
            keko.add(solmu);
        }
        
        solmu1 = keko.poll();
        
        while(!keko.isEmpty()) {
            solmu2 = keko.poll();
            
            assertTrue(solmu1.getDistance() <= solmu2.getDistance());
            solmu1 = solmu2;
        }
    }
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
