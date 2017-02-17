package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.WordSequencer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PasteTextActionListener implements ActionListener {

    private WordSequencer ws;
    private JLabel bigWord;

    public PasteTextActionListener(WordSequencer wordSequencer, JLabel label) {
        ws = wordSequencer;
        bigWord = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ws.setRunning(false);
        String input = JOptionPane.showInputDialog(null, "Paste text below");
        if (input == null) {
            return;
        }
        List<String> list = new ArrayList<>();

        for (String s : input.split(" ")) {
            list.add(s);
        }
        String first = ws.toBeginningOfText();

        // Miksi ei vaihda?
        bigWord.setText(first);
    }
}
