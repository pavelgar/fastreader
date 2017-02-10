package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.Commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForwardsActionListener implements ActionListener {

    private Commands commands;

    public ForwardsActionListener(Commands commands) {
        this.commands = commands;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commands.toNextSentence();
    }
}
