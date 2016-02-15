/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Polkualgoritmien_vertailu.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class ASolmuTest {
    
    ASolmu solmu1;
    ASolmu solmu2;
    
    public ASolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        solmu1 = new ASolmu(1,3);
        solmu2 = new ASolmu(2,3);
        
        solmu1.setDistance(3);
        solmu2.setDistance(2);
        
        solmu1.setDistanceToGo(5);
        solmu2.setDistanceToGo(5);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void solmujenVertailuToimiiOikein() {
        
        List<ASolmu> lista = new ArrayList();
        lista.add(solmu1);
        lista.add(solmu2);
        
        Collections.sort(lista);
        
        assertEquals(2, lista.get(0).getX());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
