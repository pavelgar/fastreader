package pavelgarmuyev.fastreader.applogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackwardsActionListener implements ActionListener {

    private Commands commands;

    public BackwardsActionListener(Commands commands) {
        this.commands = commands;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commands.toBeginningOfCurrentSentence();
    }
}
