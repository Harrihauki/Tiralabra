/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.Sokkelogeneraattori;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Myllyaho
 */
public class SokkelogeneraattoriTest {
    
    Sokkelogeneraattori generaattori;
    Random arpoja;
    
    public SokkelogeneraattoriTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        generaattori = new Sokkelogeneraattori();
        arpoja = new Random();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void maaliMeneeOikein() {
        
        int x = arpoja.nextInt(998) + 2;
        int y = arpoja.nextInt(998) + 2;
        int lahtoX = arpoja.nextInt(x-2) + 1;
        int lahtoY = arpoja.nextInt(y-2) + 1;
        char[][] kartta = generaattori.luoKartta(x, y, 0, 0, lahtoX, lahtoY);
        
        assertEquals(kartta[lahtoX][lahtoY], '.');
    }
    
    @Test
    public void lahtoMeneeOikein() {
        
        int x = arpoja.nextInt(998) + 2;
        int y = arpoja.nextInt(998) + 2;
        int lahtoX = arpoja.nextInt(x-2) + 1;
        int lahtoY = arpoja.nextInt(y-2) + 1;
        char[][] kartta = generaattori.luoKartta(x, y, lahtoX, lahtoY, 0, 0);
        
        assertEquals(kartta[lahtoX][lahtoY], '.');
    }
    
    @Test
    public void seinatOikein() {
        
        char[][] kartta = generaattori.luoKartta(50, 50, 3, 3, 5, 5);
        
        for (int i = 0; i < kartta.length; i++) {
            
            assertEquals(kartta[0][i], '#');
            assertEquals(kartta[i][0], '#');
            assertEquals(kartta[i][kartta[0].length-1], '#');
            assertEquals(kartta[kartta.length-1][i], '#');
        }
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
