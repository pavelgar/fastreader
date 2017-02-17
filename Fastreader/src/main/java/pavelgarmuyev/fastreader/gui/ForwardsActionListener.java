package pavelgarmuyev.fastreader.gui;


import pavelgarmuyev.fastreader.applogic.WordSequencer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForwardsActionListener implements ActionListener {

    private WordSequencer wordSequencer;
    private JLabel bigWord;

    public ForwardsActionListener(WordSequencer wordSequencer, JLabel label) {
        this.wordSequencer = wordSequencer;
        bigWord = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        wordSequencer.setRunning(false);
        bigWord.setText(wordSequencer.nextSentenceBeginning());
    }
}
