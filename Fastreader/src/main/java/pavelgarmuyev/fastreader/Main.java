
package pavelgarmuyev.fastreader;

import pavelgarmuyev.fastreader.applogic.FileOpener;
import pavelgarmuyev.fastreader.applogic.WordSequencer;
import pavelgarmuyev.fastreader.gui.*;
import javax.swing.SwingUtilities;

public class Main {
    
    public static void main(String[] args) {


        FileOpener fo = new FileOpener();
        WordSequencer ws = new WordSequencer(fo.openFile("src/main/resources/tutorial.txt"));
        UserInterface ui = new UserInterface(ws, fo);

        SwingUtilities.invokeLater(ui);


        while (true) {
            // Sanojen päivitys toimii vain, jos System.out.print() kutsutaan...
            System.out.print("");

            if (ws.isRunning()) {
                ui.setBigWord(ws.nextWord());
                try {
                    double time = 60d / ws.getSpeed() * 1000;
                    Thread.sleep((int) time);
                } catch (InterruptedException e) {
                    System.out.println("Printing error");
                }
            }
        }
    }
}
