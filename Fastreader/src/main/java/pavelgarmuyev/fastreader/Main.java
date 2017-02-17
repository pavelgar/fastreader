
package pavelgarmuyev.fastreader;

import pavelgarmuyev.fastreader.applogic.FileOpener;
import pavelgarmuyev.fastreader.applogic.WordSequencer;
import pavelgarmuyev.fastreader.gui.*;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {


        FileOpener fo = new FileOpener();
        WordSequencer ws = new WordSequencer(fo.openFile("test.txt"));
        UserInterface ui = new UserInterface(ws, fo);

        SwingUtilities.invokeLater(ui);

        while (true) {

            System.out.println("jotain");

            if (ws.isRunning()) {
                ui.setBigWord(ws.nextWord());
                try {
                    Thread.sleep(ws.getSpeed() / 60 * 1000);
                } catch (InterruptedException e) {
                    System.out.println("Printing error");
                }
            }
        }
    }
}
