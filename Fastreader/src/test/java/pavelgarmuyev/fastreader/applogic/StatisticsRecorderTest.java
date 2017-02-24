package pavelgarmuyev.fastreader.applogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    }

    @Before
    public void setUp() {
        stats = new StatisticsRecorder(propertiesPath);
    }

    @After
    public void tearDown() {
        File file = new File(propertiesPath);
        file.delete();

    }

    @Test
    public void ifFileExistsConstructorTest() throws FileNotFoundException {
        File file = new File(propertiesPath);
        Scanner scanner = new Scanner(file);
        scanner.nextLine();
        String first = "preferred_speed";
        String second = "stat_words_read";
        String third = "stat_pauses_made";
        
        List<String> tests = new ArrayList<>();
        tests.add(first);
        tests.add(second);
        tests.add(third);
        
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.contains(first)) {
                tests.remove(first);
            } else if (nextLine.contains(second)) {
                tests.remove(second);
            } else if (nextLine.contains(third)) {
                tests.remove(third);
            }
        }
        assertEquals(true, tests.isEmpty());
    }
    
    @Test
    public void setPreferredSpeedTest() {
        stats.setPreferredSpeed(1000000);
        assertEquals(1000000, stats.getPreferredSpeed());
    }
    
    public void incrementWordsReadTest() throws FileNotFoundException {
        stats.incrementWordsRead();
        File file = new File(propertiesPath);
        Scanner scanner = new Scanner(file);
        boolean contains = false;
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.contains("words_read=")) {
                if (str.charAt(str.length() - 1) == '1') {
                    contains = true;
                } 
            }
        }
        assertEquals(true, contains);
    }
    
    public void incrementPausesMadeTest() throws FileNotFoundException {
        stats.incrementPausesMade();
        File file = new File(propertiesPath);
        Scanner scanner = new Scanner(file);
        boolean contains = false;
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (str.contains("pauses_made=")) {
                if (str.charAt(str.length() - 1) == '1') {
                    contains = true;
                } 
            }
        }
        assertEquals(true, contains);
    }
}
