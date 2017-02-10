
package pavelgarmuyev.fastreader;

import pavelgarmuyev.fastreader.applogic.Commands;
import pavelgarmuyev.fastreader.applogic.WordSequencer;
import pavelgarmuyev.fastreader.gui.*;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String filePath = "test.txt";
        Scanner fileScanner = null;

        try {
            fileScanner = new Scanner(new File(filePath));

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        List<String> list = new ArrayList<>();
        while (fileScanner.hasNext()) {
            list.add(fileScanner.next());
        }

        WordSequencer ws = new WordSequencer(list);
        Commands commands = new Commands(ws);
        UserInterface ui = new UserInterface(commands);

        SwingUtilities.invokeLater(ui);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            if (input.isEmpty()) {
                ws.nextWord();
            }
        }
    }
}
