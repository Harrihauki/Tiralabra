package Polkualgoritmien_vertailu.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class SolmuTest {
    
    Solmu solmu1;
    Solmu solmu2;
    
    public SolmuTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        solmu1 = new Solmu(1,3);
        solmu2 = new Solmu(2,3);
        
        solmu1.setDistance(3);
        solmu2.setDistance(2);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void solmujenVertailuToimiiOikein() {
        
        List<Solmu> lista = new ArrayList();
        lista.add(solmu1);
        lista.add(solmu2);
        
        Collections.sort(lista);
        
        assertEquals(2, lista.get(0).getX());
    }
}
