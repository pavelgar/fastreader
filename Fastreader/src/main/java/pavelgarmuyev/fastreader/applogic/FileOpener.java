package pavelgarmuyev.fastreader.applogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOpener {
    /**
     * Luo uuden FileOpener olion.
     */
    public FileOpener() {
    }
    /**
     * Avaa tiedoston ja tallentaa jokaisen sanan listaan.
     *
     * @param path      Tiedoston sijainti kovalevyllä.
     * @return          Lista tiedoston sanoista.
     */
    public List<String> openFile(String path) {
        List<String> list = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(path));
            while (fileScanner.hasNextLine()) {
                for (String s : fileScanner.nextLine().split(" ")) {
                    list.add(s);
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
        }

        return list;
    }
}
