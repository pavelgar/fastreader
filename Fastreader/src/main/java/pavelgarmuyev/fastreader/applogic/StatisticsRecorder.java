package pavelgarmuyev.fastreader.applogic;

import java.io.*;
import java.util.Properties;

public class StatisticsRecorder {

    private Properties properties;
    private String path;
    private File file;
    private InputStream input;
    private OutputStream output;

    // Property names:
    private final String preferredSpeed = "preferred_speed";
    private final String wordsRead = "words_read";
    private final String pausesMade = "pauses_made";

    /**
     * Luo uuden properties tallentavan olion.
     */

    public StatisticsRecorder(String propertiesPath) {
        properties = new Properties();
        path = propertiesPath;
        file = new File(path);

        if (!file.exists()) {
            createDefaults();
        } else {
            loadProperties();
        }
    }

    private void createDefaults() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Couldn't create new file " + path);
        }

        try {
            output = new FileOutputStream(file);
            properties.setProperty(preferredSpeed, "100");
            properties.setProperty(wordsRead, "0");
            properties.setProperty(pausesMade, "0");

            properties.store(output, null);

        } catch (IOException e) {
            System.out.println("Couldn't write to " + path);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close OutputStream");
                }
            }
        }
    }

    private void loadProperties() {
        try {
            input = new FileInputStream(file);

            properties.load(input);

            for (String s : properties.stringPropertyNames())
                System.out.println(s);

        } catch (IOException e) {
            System.out.println("Couldn't load properties " + path);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close FileInputStream");
                }
            }
        }
    }
}
