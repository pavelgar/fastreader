package pavelgarmuyev.fastreader.applogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOpener {

    public FileOpener() {

    }

    public List<String> openFile(String path) {
        List<String> list = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File(path));

            while (fileScanner.hasNext()) {
                list.add(fileScanner.next());
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return list;
    }
}
