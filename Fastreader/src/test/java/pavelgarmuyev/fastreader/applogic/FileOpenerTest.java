package pavelgarmuyev.fastreader.applogic;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
    public void openNotExistingFileTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        List<String> list = fo.openFile("openFileTest2.txt");
        assertEquals(true, out.toString().contains("File not found."));
        assertEquals(null, list);
    }
}
