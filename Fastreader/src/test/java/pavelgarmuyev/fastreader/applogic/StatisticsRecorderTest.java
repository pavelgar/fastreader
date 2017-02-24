package pavelgarmuyev.fastreader.applogic;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

public class StatisticsRecorderTest {
    
    private StatisticsRecorder stats;
    private static String propertiesPath = "src/main/resources/test.properties";
    
    public StatisticsRecorderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        File file = new File(propertiesPath);
        file.delete();
    }

    @Before
    public void setUp() {
        stats = new StatisticsRecorder(propertiesPath);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void ifFileExistsConstructorTest() {
        File file = new File(propertiesPath);
        assertEquals(true, file.exists());
    }
}
