
package pavelgarmuyev.fastreader.applogic;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSequencerTest {

    private static List<String> list = Arrays.asList("This?", "Is", "a", "serious", "test.", "Please,", "kill", "all", "mutants!");
    private WordSequencer ws;

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
        ws = new WordSequencer(list);
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void totalWordsTest() {
        assertEquals(9, ws.totalWords());
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
    public void setIndexEqualToListSizeTest() {
        ws.setIndex(list.size());
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
        ws = new WordSequencer(new ArrayList<>());
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
    public void prevWordWhenListIsEmptyTest() {
        ws = new WordSequencer(new ArrayList<>());
        assertEquals(null, ws.prevWord());
    }

    @Test
    public void nextSentenceBeginningTest() {
        assertEquals("Is", ws.nextSentenceBeginning());
    }

    @Test
    public void currentSentenceBeginningTest() {
        ws.setIndex(6);
        assertEquals("Please,", ws.currentSentenceBeginning());
    }

    @Test
    public void currentSentenceBeginningWhenOnTheRightWordTest() {
        ws.setIndex(1);
        assertEquals("Is", ws.currentSentenceBeginning());
    }

    @Test
    public void currentSentenceBeginningWhenReachedZeroTest() {
        ws = new WordSequencer(Arrays.asList("This", "is", "a", "test!"));
        ws.setIndex(3);
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
}