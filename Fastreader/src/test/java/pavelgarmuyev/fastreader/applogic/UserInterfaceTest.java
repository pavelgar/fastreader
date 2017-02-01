
package pavelgarmuyev.fastreader.applogic;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
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
public class UserInterfaceTest {
    
    public UserInterfaceTest() {
        
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
    public void askSpeedPalauttaaSyotetynLuvun() {
        ByteArrayInputStream in = new ByteArrayInputStream("100".getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);
        assertEquals(100, ui.askSpeed());
    }
    
    @Test
    public void askSpeedKysyyUudelleenVaarallaSyotteella() {
        ByteArrayInputStream in = new ByteArrayInputStream("abc\n\n200".getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);
        assertEquals(200, ui.askSpeed());
    }
    
    @Test
    public void askSpeedKysyyUudelleenNegatiivisella() {
        ByteArrayInputStream in = new ByteArrayInputStream("-10\n-1000\n200".getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);
        assertEquals(200, ui.askSpeed());
    }
    
    @Test
    public void askTextPalautaaSyotetynTekstin() {
        ByteArrayInputStream in = new ByteArrayInputStream("abc1".getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);
        assertEquals("abc1", ui.askText());
    }
    
    @Test
    public void askTextKysyyUudestaanTyhjallaSyotteella() {
        ByteArrayInputStream in = new ByteArrayInputStream("\n\n\nabc2".getBytes());
        Scanner scanner = new Scanner(in);
        UserInterface ui = new UserInterface(scanner);
        assertEquals("abc2", ui.askText());
    }
}
