
package pavelgarmuyev.fastreader;

import pavelgarmuyev.fastreader.applogic.FileOpener;
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
        List<String> list = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File("test.txt"));

            while (fileScanner.hasNext()) {
                list.add(fileScanner.next());
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return;
        }

        FileOpener fo = new FileOpener();
        WordSequencer ws = new WordSequencer(list);
        UserInterface ui = new UserInterface(ws, fo);

        SwingUtilities.invokeLater(ui);

        while (true) {

            // System.out.println("jotain");
            
            if (ws.isRunning()) {
                ui.setBigWord(ws.nextWord());
                try {
                    Thread.sleep(ws.getSpeed() / 60 * 100);
                } catch (InterruptedException e) {
                    System.out.println("Printing error");
                }
            }
        }
    }
}
