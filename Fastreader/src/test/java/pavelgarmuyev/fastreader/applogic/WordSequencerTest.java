
package pavelgarmuyev.fastreader.applogic;

import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author pavelgarmuyev
 */
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
    public void konstruktoriListaaSanat() {
        WordSequencer ws = new WordSequencer("Get thiS! , .", 100);
        assertEquals(Arrays.asList("Get","thiS!",",","."), ws.getWords());
    }
    
    @Test
    public void konstruktoriNostaaNopeuden() {
        WordSequencer ws = new WordSequencer("Get thiS! , .", 50);
        assertEquals(100, ws.getSpeed());
    }
    
    @Test
    public void konstruktoriLaskeeNopeuden() {
        WordSequencer ws = new WordSequencer("Get thiS! , .", 550);
        assertEquals(500, ws.getSpeed());
    }
    
    @Test
    public void konstruktoriEiMuutaValidiaNopeutta() {
        WordSequencer ws = new WordSequencer("Get thiS! , .", 250);
        assertEquals(250, ws.getSpeed());
    }
    
    @Test
    public void setSpeedAsettaaNopeuden() {
        WordSequencer ws = new WordSequencer("Get thiS! , .", 100);
        ws.setSpeed(300);
        assertEquals(300, ws.getSpeed());
    }
}