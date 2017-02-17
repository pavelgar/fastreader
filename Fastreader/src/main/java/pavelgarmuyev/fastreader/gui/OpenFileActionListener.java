package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.FileOpener;
import pavelgarmuyev.fastreader.applogic.WordSequencer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFileActionListener implements ActionListener {

    private FileOpener fo;
    private WordSequencer ws;

    public OpenFileActionListener(WordSequencer wordSequencer, FileOpener fileOpener) {
        fo = fileOpener;
        ws = wordSequencer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String path = JOptionPane.showInputDialog(null, "Input full path to file");
    }
}
