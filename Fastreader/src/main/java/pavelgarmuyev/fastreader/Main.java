
package pavelgarmuyev.fastreader;

import pavelgarmuyev.fastreader.applogic.WordSequencer;
import pavelgarmuyev.fastreader.gui.*;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);

        System.out.println("Input path to file");
        System.out.print("> ");
        String filePath = scanner.nextLine();
        if (!filePath.endsWith(".txt")) {
            filePath.concat(".txt");
        }

        File file = new File(filePath);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        List<String> wordList = new LinkedList<>();
        while (fileScanner.hasNext()) {
            wordList.add(fileScanner.next());
        }
        WordSequencer ws = new WordSequencer(wordList, 100);
        ws.outputWords();

    }
}
