package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.*;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WordSequencer wordSequencer;

    public UserInterface(WordSequencer wordSequencer) {
        this.wordSequencer = wordSequencer;
    }

    /**
     * Aloittaa uuden JFramen luonnin.
     */
    @Override
    public void run() {
        frame = new JFrame("FastReader");
        frame.setPreferredSize(new Dimension(500, 300));

        frame.getContentPane().setBackground(Color.WHITE);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setJMenuBar(createMenuBar());
        createComponents(frame.getContentPane());

        frame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)(dimension.getWidth() - frame.getWidth()) / 2,
                        (int)(dimension.getHeight() - frame.getHeight()) / 2);
        frame.setVisible(true);

    }

    private void createComponents(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(createBigWord());
        container.add(createControls());
        container.add(Box.createVerticalGlue());

        container.add(Box.createVerticalGlue());
        container.add(createProgressBar());
    }

    private JProgressBar createProgressBar() {
        JProgressBar progressBar = new JProgressBar(0, wordSequencer.totalWords());
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setAlignmentX(0.9f);

        return progressBar;
    }

    private JPanel createBigWord() {

        JPanel bigWordPanel = new JPanel();
        bigWordPanel.setLayout(new BoxLayout(bigWordPanel, BoxLayout.X_AXIS));

        JLabel bigWord = new JLabel("text", SwingConstants.CENTER);
        bigWord.setAlignmentX(0.5f);
        bigWord.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        bigWord.setMinimumSize(new Dimension(300, 100));
        bigWord.setMaximumSize(new Dimension(300, 100));
        bigWord.setFont(new Font("sans-serif", Font.PLAIN, 50));
        bigWord.setBackground(Color.WHITE);
        bigWord.setOpaque(true);

        bigWordPanel.add(bigWord);
        return bigWordPanel;
    }

    private JPanel createControls() {

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.X_AXIS));
        controlsPanel.setBackground(Color.WHITE);

        JButton[] buttons = new JButton[4];

        JButton rewindButton = new JButton(new ImageIcon("assets/rewind_button.png"));
        rewindButton.addActionListener(new RewindActionListener(wordSequencer));
        buttons[0] = rewindButton;

        JButton prevButton = new JButton(new ImageIcon("assets/prev_sentence_button.png"));
        prevButton.addActionListener(new BackwardsActionListener(wordSequencer));
        buttons[1] = prevButton;

        JButton playButton = new JButton(new ImageIcon("assets/play_button.png"));
        playButton.addActionListener(new PlayActionListener(wordSequencer));
        buttons[2] = playButton;

        JButton nextButton = new JButton(new ImageIcon("assets/next_sentence_button.png"));
        nextButton.addActionListener(new ForwardsActionListener(wordSequencer));
        buttons[3] = nextButton;

        for (JButton jb : buttons) {
            jb.setOpaque(false);
            jb.setContentAreaFilled(false);
            jb.setBorderPainted(false);
            controlsPanel.add(jb);
        }
        return controlsPanel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        menuBar.add(fileMenu);

        JMenuItem openMenuItem = new JMenuItem("Open File");
        fileMenu.add(openMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save state");
        fileMenu.add(saveMenuItem);

        JMenu recentSubMenu = new JMenu("Open recent");

        JMenuItem recentFile = new JMenuItem("/path/to/file.txt");

        recentSubMenu.add(recentFile);
        fileMenu.add(recentSubMenu);

        return menuBar;
    }
}
