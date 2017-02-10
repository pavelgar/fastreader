
package pavelgarmuyev.fastreader.applogic;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordSequencerTest {

    public WordSequencerTest() {
        
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

    @Test
    public void nextWordAntaaSeuraavanSanan() {
        WordSequencer ws = new WordSequencer("Get thiS! , .");
        assertEquals("Get", ws.nextWord());
    }

    @Test
    public void nextWordPalauttaaNullKunSanatLoppu() {
        WordSequencer ws = new WordSequencer("Get thiS!");
        ws.nextWord();
        ws.nextWord();
        assertEquals(null, ws.nextWord());
    }
}