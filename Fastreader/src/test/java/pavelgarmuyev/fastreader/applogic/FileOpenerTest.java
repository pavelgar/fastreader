package pavelgarmuyev.fastreader.applogic;

import org.junit.*;

import java.util.Arrays;
import java.util.List;

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
        List<String> list = fo.openFile("openFileTest.txt");
        assertEquals(Arrays.asList("This", "file", "is", "a", "test!"), list);
    }

    @Test
    public void openFileClosesTest() {
        List<String> list = fo.openFile("openFileTest.txt");
        assertEquals(Arrays.asList("This", "file", "is", "a", "test!"), list);
    }
}
