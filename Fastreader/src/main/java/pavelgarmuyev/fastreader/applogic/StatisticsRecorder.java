package pavelgarmuyev.fastreader.applogic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class StatisticsRecorder {

    private Properties defaults;

    public StatisticsRecorder() {
        // Onko tämä luokka tehty oikein? Tarkoituksena käyttää .properties -tiedostoa

        defaults = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream("src/main/resources/default.properties.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find default properties file");
        }
        try {
            defaults.load(in);
        } catch (IOException e) {
            System.out.println("No properties found or syntax error");
        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Can't close default properties file");
        }
        Properties appConfig = new Properties(defaults);

        try {
            in = new FileInputStream("src/main/resources/default.properties.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Can't find application's properties file");
        }
        try {
            appConfig.load(in);
        } catch (IOException e) {
            System.out.println("Syntax error");
        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("Can't close properties file");
        }
    }
}
