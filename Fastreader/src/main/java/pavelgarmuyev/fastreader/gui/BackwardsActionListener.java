package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.WordSequencer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackwardsActionListener implements ActionListener {

    private WordSequencer wordSequencer;

    public BackwardsActionListener(WordSequencer wordSequencer) {
        this.wordSequencer = wordSequencer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
