
package pavelgarmuyev.fastreader;

import pavelgarmuyev.fastreader.applogic.Commands;
import pavelgarmuyev.fastreader.applogic.WordSequencer;
import pavelgarmuyev.fastreader.gui.*;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Input path to file");
        System.out.print("> ");
        // String filePath = scanner.nextLine();
        String filePath = "test.txt";

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        List<String> wordList = new LinkedList<>();
        while (fileScanner.hasNext()) {
            String s = fileScanner.next();
            wordList.add(s);
            System.out.println(s);
        }
        WordSequencer ws = new WordSequencer(wordList);
        Commands commands = new Commands(ws);
        UserInterface ui = new UserInterface(commands);
        SwingUtilities.invokeLater(ui);
    }
}
