package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.*;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WordSequencer wordSequencer;
    private FileOpener fileOpener;
    private StatisticsRecorder stats;
    private JLabel bigWord;

    public UserInterface(WordSequencer ws, FileOpener fo) {
        wordSequencer = ws;
        fileOpener = fo;
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

    public void setBigWord(String word) {
        bigWord.setText(word);
    }

    private void createComponents(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(createBigWord());
        container.add(createControls());
        container.add(Box.createVerticalGlue());
        container.add(createSpeedSetter());
        container.add(createProgressBar());
    }

    private JPanel createSpeedSetter() {
        JPanel speedPanel = new JPanel();
        speedPanel.setBackground(Color.WHITE);
        speedPanel.setOpaque(true);
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.X_AXIS));

        JLabel speedLabel = new JLabel("Speed: ");

        speedPanel.add(speedLabel);

        JTextField speedSetter = new JTextField();
        speedSetter.addActionListener(new SpeedSetterActionListener(wordSequencer, speedSetter));
        speedSetter.setMaximumSize(new Dimension(40, 14));
        speedSetter.setMinimumSize(new Dimension(40, 14));
        speedSetter.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        speedSetter.setText(wordSequencer.getSpeed() + "");

        speedPanel.add(speedSetter);

        return speedPanel;
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

        bigWord = new JLabel(wordSequencer.toBeginningOfText(), SwingConstants.CENTER);
        bigWord.setAlignmentX(0.5f);
        bigWord.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        bigWord.setMinimumSize(new Dimension(400, 100));
        bigWord.setMaximumSize(new Dimension(400, 100));
        bigWord.setFont(new Font("sans-serif", Font.PLAIN, 48));
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

        JButton rewindButton = new JButton(new ImageIcon("src/main/resources/rewind_button.png"));
        rewindButton.addActionListener(new RewindActionListener(wordSequencer, bigWord));
        buttons[0] = rewindButton;

        JButton prevButton = new JButton(new ImageIcon("src/main/resources/prev_sentence_button.png"));
        prevButton.addActionListener(new BackwardsActionListener(wordSequencer, bigWord));
        buttons[1] = prevButton;

        JButton playButton = new JButton(new ImageIcon("src/main/resources/play_button.png"));
        playButton.addActionListener(new PlayActionListener(wordSequencer));
        buttons[2] = playButton;

        JButton nextButton = new JButton(new ImageIcon("src/main/resources/next_sentence_button.png"));
        nextButton.addActionListener(new ForwardsActionListener(wordSequencer, bigWord));
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

        JMenuItem openFileItem = new JMenuItem("Open File");
        openFileItem.addActionListener(new OpenFileActionListener(wordSequencer, fileOpener));

        menuBar.add(openFileItem);

        JMenuItem statisticsItem = new JMenuItem("Statistics");
        statisticsItem.addActionListener(new StatisticsActionListener());

        menuBar.add(statisticsItem);

        return menuBar;
    }
}
