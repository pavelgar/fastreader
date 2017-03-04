package pavelgarmuyev.fastreader.applogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        File file = new File(propertiesPath);
        file.delete();
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
    public void constructorTest() throws FileNotFoundException {
        File file = new File(propertiesPath);
        Scanner scanner = new Scanner(file);
        String first = "preferred_speed=100";
        String second = "stat_words_read=0";
        String third = "stat_pauses_made=0";
        List<String> tests = new ArrayList<>();
        tests.add(first);
        tests.add(second);
        tests.add(third);
        
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.equals(first)) {
                tests.remove(first);
            } else if (nextLine.equals(second)) {
                tests.remove(second);
            } else if (nextLine.equals(third)) {
                tests.remove(third);
            }
        }
        scanner.close();
        assertEquals(true, tests.isEmpty());
    }
    
    @Test
    public void constructorTest2() throws IOException {
        String first = "preferred_speed=1000";
        String second = "stat_words_read=123";
        String third = "stat_pauses_made=5";
        List<String> tests = new ArrayList<>();
        tests.add(first);
        tests.add(second);
        tests.add(third);
        
        File file = new File(propertiesPath);
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        fw.write(first + "\n");
        fw.write(second + "\n");
        fw.write(third + "\n");
        fw.close();
        stats = new StatisticsRecorder(propertiesPath);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.equals(first)) {
                tests.remove(first);
            } else if (nextLine.equals(second)) {
                tests.remove(second);
            } else if (nextLine.equals(third)) {
                tests.remove(third);
            }
        }
        scanner.close();
        assertEquals(true, tests.isEmpty());
        
        file.delete();
        file.createNewFile();
        
        fw = new FileWriter(file);
        fw.write(first + "\n");
        fw.write(second + "\n");
        fw.close();
        third = "stat_pauses_made=0";
        tests = new ArrayList<>();
        tests.add(first);
        tests.add(second);
        tests.add(third);
        
        stats = new StatisticsRecorder(propertiesPath);
        scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.equals(first)) {
                tests.remove(first);
            } else if (nextLine.equals(second)) {
                tests.remove(second);
            } else if (nextLine.equals(third)) {
                tests.remove(third);
            }
        }
        scanner.close();
        assertEquals(true, tests.isEmpty());
    }
    
    @Test
    public void constructorTest3() throws IOException {
        String first = "preferred_speed=1000";
        String second = "stat_words_read=123";
        String third = "stat_pauses_made=5";
        String alien = "random_property=666";
        List<String> tests = new ArrayList<>();
        tests.add(first);
        tests.add(second);
        tests.add(third);
        tests.add(alien);
        
        File file = new File(propertiesPath);
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        
        FileWriter fw = new FileWriter(file);
        fw.write(first + "\n");
        fw.write(second + "\n");
        fw.write(third + "\n");
        fw.write(alien);
        fw.close();
        stats = new StatisticsRecorder(propertiesPath);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.equals(first)) {
                tests.remove(first);
            } else if (nextLine.equals(second)) {
                tests.remove(second);
            } else if (nextLine.equals(third)) {
                tests.remove(third);
            }
        }
        scanner.close();
        assertEquals(true, tests.isEmpty());
    }
    
    @Test
    public void setPreferredSpeedTest() {
        stats.setPreferredSpeed(1000000);
        assertEquals(1000000, stats.getPreferredSpeed());
    }
    
    @Test
    public void setPreferredSpeedUpdatesPropertiesTest() throws FileNotFoundException {
        stats.setPreferredSpeed(1000000);
        File file = new File(propertiesPath);
        Scanner scanner = new Scanner(file);
        int value = 0;
        String propertyName = "preferred_speed=";
        while (scanner.hasNextLine()) {
            String property = scanner.nextLine();
            if (property.startsWith(propertyName)) {
                value = Integer.parseInt(property.substring(propertyName.length()));
            }
        }
        scanner.close();
        assertEquals(1000000, value);
    }
    
    public void incrementWordsReadTest() {
        Map<String, String> statistics = stats.getStatistics();
        stats.incrementWordsRead();
        assertEquals(1, Integer.parseInt(statistics.get("words_read")));
    }
    
    public void incrementPausesMadeTest() {
        Map<String, String> map = stats.getStatistics();
        int value = Integer.parseInt(map.get("pauses_made"));
        stats.incrementPausesMade();
        map = stats.getStatistics();
        int newValue = Integer.parseInt(map.get("pauses_made"));
        assertEquals(value + 1, newValue);
    }
}
