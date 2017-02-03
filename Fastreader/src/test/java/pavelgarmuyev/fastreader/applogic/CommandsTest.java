package pavelgarmuyev.fastreader.applogic;


import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class CommandsTest {

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
    public void asettaaAlkuun() {
        WordSequencer ws = new WordSequencer("Get that!");
        Commands commands = new Commands(ws);
        ws.nextWord();
        ws.nextWord();
        commands.toTextBeginning();
        assertEquals(0, ws.getPos());
    }

    @Test
    public void asettaaLauseenAlkuun() {
        WordSequencer ws = new WordSequencer("Get. Get that! Try this?");
        Commands commands = new Commands(ws);
        ws.nextWord();
        ws.nextWord();
        ws.nextWord();
        commands.toBeginningOfCurrentSentence();
        assertEquals(1, ws.getPos());
    }

    @Test
    public void asettaaSeuraavanLauseenAlkuun() {
        WordSequencer ws = new WordSequencer("Get. Get that! Try this?");
        Commands commands = new Commands(ws);
        ws.nextWord();
        ws.nextWord();
        commands.toNextSentence();
        assertEquals(3, ws.getPos());
    }
}
