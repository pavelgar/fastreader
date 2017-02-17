package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.WordSequencer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpeedSetterActionListener implements ActionListener {

    private WordSequencer wordSequencer;
    private JTextField text;

    public SpeedSetterActionListener(WordSequencer wordSequencer, JTextField jTextField) {
        this.wordSequencer = wordSequencer;
        text = jTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = text.getText();
        if (input.matches("[0-9]+")) {
            wordSequencer.setSpeed(Integer.parseInt(input));
        } else {
            text.setText(wordSequencer.getSpeed() + "");
        }
    }
}
