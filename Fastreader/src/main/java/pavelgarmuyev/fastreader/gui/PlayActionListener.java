package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.WordSequencer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayActionListener  implements ActionListener {

    private WordSequencer wordSequencer;

    public PlayActionListener(WordSequencer wordSequencer) {
        this.wordSequencer = wordSequencer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wordSequencer.setRunning(!wordSequencer.isRunning());
    }
}
