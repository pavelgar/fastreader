package pavelgarmuyev.fastreader.applogic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class StatisticsRecorder {

    private Properties properties;
    private String path;
    private File file;
    private InputStream input;
    private OutputStream output;

    // Property names:
    private final String preferredSpeed = "preferred_speed";
    private final String wordsRead = "stat_words_read";
    private final String pausesMade = "stat_pauses_made";

    private Map<String, String> defaults;

    /**
     * Luo uuden olion, joka tallentaa statistiikaa ohjelman käyttäjästä properties -tiedostoon.
     * @param propertiesPath    Avattavan/luotavan properties -tiedoston polku.
     */
    public StatisticsRecorder(String propertiesPath) {
        defaults = new HashMap<>();

        // Default values
        defaults.put(preferredSpeed, "100");
        defaults.put(wordsRead, "0");
        defaults.put(pausesMade, "0");

        properties = new Properties();
        path = propertiesPath;
        file = new File(path);

        if (!file.exists()) {
            createDefaults();
        }
        loadProperties();
        checkProperties();
    }

    private void createDefaults() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Couldn't create new file " + path);
        }

        try {
            output = new FileOutputStream(file);

            for (String key : defaults.keySet()) {
                properties.setProperty(key, defaults.get(key));
            }
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

    private void checkProperties() {
        for (String key : defaults.keySet()) {
            if (!properties.containsKey(key)) {
                properties.setProperty(key, defaults.get(key));
            }
        }
        for (String key : properties.stringPropertyNames()) {
            if (!defaults.containsKey(key)) {
                properties.remove(key);
            }
        }
        updateProperties();
    }

    private void loadProperties() {
        try {
            input = new FileInputStream(file);

            properties.load(input);

        } catch (IOException e) {
            System.out.println("Couldn't load properties");
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

    private void updateProperties() {
        try {
            output = new FileOutputStream(file);
            properties.store(output, null);

        } catch (IOException e) {
            System.out.println("Couldn't save properties");
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close FileOutputStream");
                }
            }
        }
    }

    /**
     * Tallentaa asetetun nopeuden.
     * @param value     Tallennettavan nopeuden arvo.
     */
    public void setPreferredSpeed(int value) {
        properties.setProperty(preferredSpeed, Integer.toString(value));
        updateProperties();
    }

    /**
     * Hakee tallennetun nopeudeun.
     * @return  Nopeus.
     */
    public int getPreferredSpeed() {
        return Integer.parseInt(properties.getProperty(preferredSpeed));
    }

    /**
     * Kasvattaa luettujen sanojen määrää yhdellä ja tallentaa uuden arvon.
     */
    public void incrementWordsRead() {
        int total = Integer.parseInt(properties.getProperty(wordsRead));
        properties.setProperty(wordsRead, Integer.toString(total + 1));
        updateProperties();
    }

    /**
     * Kasvattaa tehtyjen taukojen määrää yhdellä ja tallentaa uuden arvon.
     */
    public void incrementPausesMade() {
        int total = Integer.parseInt(properties.getProperty(pausesMade));
        properties.setProperty(pausesMade, Integer.toString(total + 1));
        updateProperties();
    }

    /**
     * Käy läpi kaikki validit muuttujat.
     * Valitsee niistä kaikki "stat_" alkuiset sekä niiden arvot ja tallentaa uuteen hajautustauluun.
     *
     * @return  Map, jossa on avaimena muuttujan nimi ilman "stat_" alkua ja arvona alkuperäisen avaimeen liittyvä arvo.
     */
    public Map<String, String> getStatistics() {
        Map<String, String> statistics = new HashMap<>();

        for (String key : defaults.keySet()) {
            if (key.startsWith("stat_")) {
                statistics.put(key.substring(5), properties.getProperty(key));
            }
        }
        return statistics;
    }
}
