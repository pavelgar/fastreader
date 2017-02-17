package pavelgarmuyev.fastreader.applogic;

import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class FileOpenerTest {

    private FileOpener fo;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        fo = new FileOpener();
    }

    @After
    public void tearDown() {

    }

    @Test
    public void openFileTest() {
        fo.openFile("test.txt");
        assertEquals(Arrays.asList(), ws.totalWords());
    }
}
