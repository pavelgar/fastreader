
package pavelgarmuyev.fastreader.applogic;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WordSequencerTest {

    private static List<String> list = Arrays.asList("This?", "Is", "a", "serious", "test.", "Please,", "kill", "all", "mutants!");
    private static String propertiesPath = "src/main/resources/test.properties";
    private static String testFilePath = "src/main/resources/test.txt";
    private static WordSequencer ws;

    public WordSequencerTest() {
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
        ws = new WordSequencer(propertiesPath);
        ws.openPath(testFilePath);
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void getSpeedTest() {
        ws.setSpeed(1000);
        assertEquals(1000, ws.getSpeed());
    }

    @Test
    public void setIndexUnderTest() {
        ws.setIndex(Integer.MIN_VALUE);
        assertEquals(0, ws.getIndex());
    }

    @Test
    public void setIndexOverTest() {
        ws.setIndex(Integer.MAX_VALUE);
        assertEquals(list.size() - 1, ws.getIndex());
    }

    @Test
    public void setIndexNormalTest() {
        ws.setIndex(1);
        assertEquals(1, ws.getIndex());
    }

    @Test
    public void setIndexZeroTest() {
        ws.setIndex(0);
        assertEquals(0, ws.getIndex());
    }

    @Test
    public void setIndexToListSizeTest() {
        ws.setIndex(list.size());
        assertEquals(list.size() - 1, ws.getIndex());
    }

    @Test
    public void setIndexToListSizeTest2() {
        ws.setIndex(list.size() - 1);
        assertEquals(list.size() - 1, ws.getIndex());
    }

    @Test
    public void setIndexSetsRunningTest() {
        ws.setRunning(true);
        ws.setIndex(Integer.MAX_VALUE);
        assertEquals(false, ws.isRunning());
    }

    @Test
    public void nextWordTest() {
        ws.nextWord();
        assertEquals("a", ws.nextWord());
    }

    @Test
    public void nextWordWhenListIsEmptyTest() {
        ws.setList(new ArrayList<>());
        assertEquals(null, ws.nextWord());
    }

    @Test
    public void nextWordIncreasesIndexTest() {
        ws.nextWord();
        assertEquals(1, ws.getIndex());
    }

    @Test
    public void prevWordTest() {
        ws.setIndex(4);
        ws.prevWord();
        ws.prevWord();
        assertEquals("Is", ws.prevWord());
    }
    
    @Test
    public void prevWordSetIndexTest() {
        ws.setIndex(4);
        ws.prevWord();
        ws.prevWord();
        assertEquals(2, ws.getIndex());
    }
    
    @Test
    public void prevWordSetIndexZeroTest() {
        ws.setIndex(0);
        assertEquals(list.get(0), ws.prevWord());
    }

    @Test
    public void prevWordWhenListIsEmptyTest() {
        ws.setList(new ArrayList<>());
        assertEquals(null, ws.prevWord());
    }

    @Test
    public void nextSentenceBeginningTest() {
        assertEquals("Is", ws.nextSentenceBeginning());
    }
    
    @Test
    public void nextSentenceBeginningWhenNoEndTest() {
        ws.setList(Arrays.asList("this", "has", "no", "end", "char"));
        assertEquals("char", ws.nextSentenceBeginning());
        assertEquals(4, ws.getIndex());
    }
    
    @Test
    public void currentSentenceBeginningTest() {
        ws.setIndex(6);
        assertEquals("Please,", ws.currentSentenceBeginning());
    }
    
    @Test
    public void currentSentenceBeginningWhenIndexZeroTest() {
        ws.currentSentenceBeginning();
        assertEquals("This?", ws.currentSentenceBeginning());
    }
    
    @Test
    public void currentSentenceBeginningWhenReachedZeroTest() {
        ws.setList(Arrays.asList("This", "is", "a", "single", "sentence."));
        ws.currentSentenceBeginning();
        assertEquals("This", ws.currentSentenceBeginning());
        assertEquals(0, ws.getIndex());
    }

    @Test
    public void currentSentenceBeginningWhenOnTheRightWordTest() {
        ws.setIndex(1);
        assertEquals("Is", ws.currentSentenceBeginning());
    }

    @Test
    public void currentSentenceBeginningNextSentenceTest() {
        ws.setList(Arrays.asList("This", "is.", "A", "sentence", "test!"));
        ws.setIndex(2);
        assertEquals("This", ws.currentSentenceBeginning());
    }

    @Test
    public void toBeginningOfTextTest() {
        ws.setIndex(list.size() - 1);
        assertEquals("This?", ws.toBeginningOfText());
    }

    @Test
    public void setListTest() {
        ws.setList(new ArrayList<>());
        assertEquals(false, ws.isRunning());
    }
    
    @Test
    public void openPathTest() {
        assertEquals(true, ws.openPath(testFilePath));
    }
    
    @Test
    public void openPathNullTest() {
        assertEquals(false, ws.openPath(""));
    }
    
    @Test
    public void openPathEmptyTest() {
        assertEquals(false, ws.openPath("src/main/resources/emptyTest.txt"));
    }
    
    @Test
    public void getStatisticsTest() {
        File file = new File(propertiesPath);
        file.delete();
        ws = new WordSequencer(propertiesPath);
        String stats = ws.getStatistics();
        boolean contains = false;
        
        if (stats.contains("Words read: ") && stats.contains("Pauses made: ")) {
            contains = true;
        }
        assertEquals(true, contains);
    }
    
    @Test
    public void incrementPausesUpdatesStatistics() throws FileNotFoundException {
        File file = new File(propertiesPath);
        Scanner scanner = new Scanner(file);
        int value = -1;
        String pausesMade = "stat_pauses_made=";
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.startsWith(pausesMade)) {
                value = Integer.parseInt(nextLine.substring(pausesMade.length()));
            }
        }
        ws.incrementPauses();
        scanner = new Scanner(file);
        int newValue = -1;
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.startsWith(pausesMade)) {
                newValue = Integer.parseInt(nextLine.substring(pausesMade.length()));
            }
        }
        scanner.close();
        assertEquals(value + 1, newValue);
    }
    
    @Test
    public void setSpeedUpdatesStatistics() throws FileNotFoundException {
        File file = new File(propertiesPath);
        Scanner scanner = new Scanner(file);
        int value = -1;
        String wordsRead = "stat_words_read=";
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.startsWith(wordsRead)) {
                value = Integer.parseInt(nextLine.substring(wordsRead.length()));
            }
        }
        ws.nextWord();
        ws.nextWord();
        ws.nextWord();
        scanner = new Scanner(file);
        int newValue = -1;
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            
            if (nextLine.startsWith(wordsRead)) {
                newValue = Integer.parseInt(nextLine.substring(wordsRead.length()));
            }
        }
        scanner.close();
        assertEquals(value + 3, newValue);
    }
}