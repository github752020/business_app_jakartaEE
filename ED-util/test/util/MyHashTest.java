/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author elau
 */
public class MyHashTest {
    
    public MyHashTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSHA256HashedString method, of class MyHash.
     */
    @Test
    public void testGetSHA256HashedString001() {
        System.out.println("getSHA256HashedString");
        String s = "123456789";
        String expResult = "15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225";
        String result = MyHash.getSHA256HashedString(s).toLowerCase();
        assertEquals(expResult, result);
    }
 
    @Test
    public void testGetSHA256HashedString002() {
        System.out.println("getSHA256HashedString");
        String s = "1234567890";
        String expResult = "c775e7b757ede630cd0aa1113bd102661ab38829ca52a6422ab782862f268646";
        String result = MyHash.getSHA256HashedString(s).toLowerCase();
        assertEquals(expResult, result);
    }
}
