
package pavelgarmuyev.fastreader;

import pavelgarmuyev.fastreader.applogic.WordSequencer;
import pavelgarmuyev.fastreader.gui.UserInterface;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        WordSequencer ws = new WordSequencer("src/main/resources/tutorial.txt",
                "src/main/resources/config.properties");
        UserInterface ui = new UserInterface(ws);
        SwingUtilities.invokeLater(ui);

        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (ws.isRunning()) {
                SwingUtilities.invokeLater(() -> ui.getBigWord().setText(ws.nextWord()));
                try {
                    double time = 60d / ws.getSpeed() * 1000;
                    Thread.sleep((int) time);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
        }
    }
}
