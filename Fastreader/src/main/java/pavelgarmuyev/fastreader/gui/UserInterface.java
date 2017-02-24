package pavelgarmuyev.fastreader.gui;

import pavelgarmuyev.fastreader.applogic.WordSequencer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class UserInterface implements Runnable {

    private JFrame frame;
    private WordSequencer wordSequencer;
    private JLabel bigWord;

    public UserInterface(WordSequencer ws) {
        wordSequencer = ws;
    }

    /**
     * Aloittaa uuden JFramen luonnin.
     */
    @Override
    public void run() {
        frame = new JFrame("Fastreader");
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

    public JLabel getBigWord() {
        return bigWord;
    }

    private void createComponents(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createRigidArea(new Dimension(0, 30)));
        container.add(createBigWord());
        container.add(Box.createRigidArea(new Dimension(0, 20)));
        container.add(createControls());
        container.add(Box.createVerticalGlue());
        container.add(createSpeedSetter());
    }

    private JPanel createSpeedSetter() {
        JPanel speedPanel = new JPanel();
        speedPanel.setBackground(Color.WHITE);
        speedPanel.setOpaque(true);
        speedPanel.setLayout(new BoxLayout(speedPanel, BoxLayout.X_AXIS));

        JLabel speedLabel = new JLabel("Speed: ");

        speedPanel.add(speedLabel);

        JTextField speedSetter = new JTextField();
        speedSetter.addActionListener(e -> {
            String input = speedSetter.getText();
            if (input.matches("[0-9]+")) wordSequencer.setSpeed(Integer.parseInt(input));
            else speedSetter.setText(wordSequencer.getSpeed() + "");
        });
        speedSetter.setMaximumSize(new Dimension(40, 14));
        speedSetter.setMinimumSize(new Dimension(40, 14));
        speedSetter.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        speedSetter.setText(wordSequencer.getSpeed() + "");

        speedPanel.add(speedSetter);

        return speedPanel;
    }

    private JPanel createBigWord() {

        JPanel bigWordPanel = new JPanel();
        bigWordPanel.setLayout(new BoxLayout(bigWordPanel, BoxLayout.X_AXIS));

        bigWord = new JLabel(wordSequencer.toBeginningOfText(), SwingConstants.CENTER);
        bigWord.setAlignmentX(0.5f);
        bigWord.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        bigWord.setMinimumSize(new Dimension(400, 100));
        bigWord.setMaximumSize(new Dimension(400, 100));
        bigWord.setFont(new Font("sans-serif", Font.PLAIN, 42));
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
        
        InputStream is = getClass().getClassLoader().getResourceAsStream("images/rewind_button.png");
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(is);
        } catch (IOException ex) {
            System.out.println("Couldn't load rewind_button.png");
        }
        JButton rewindButton = new JButton(new ImageIcon(bf));
        rewindButton.addActionListener(e -> {
            wordSequencer.setRunning(false);
            bigWord.setText(wordSequencer.toBeginningOfText());
        });
        buttons[0] = rewindButton;
        
        is = getClass().getClassLoader().getResourceAsStream("images/prev_sentence_button.png");
        try {
            bf = ImageIO.read(is);
        } catch (IOException ex) {
            System.out.println("Couldn't load prev_sentence_button.png");
        }
        JButton prevButton = new JButton(new ImageIcon(bf));
        prevButton.addActionListener(e -> {
            wordSequencer.setRunning(false);
            bigWord.setText(wordSequencer.currentSentenceBeginning());
        });
        buttons[1] = prevButton;

        is = getClass().getClassLoader().getResourceAsStream("images/play_button.png");
        try {
            bf = ImageIO.read(is);
        } catch (IOException ex) {
            System.out.println("Couldn't load play_button.png");
        }
        JButton playButton = new JButton(new ImageIcon(bf));
        playButton.addActionListener(e -> {
            if (wordSequencer.isRunning()) wordSequencer.incrementPauses();
            wordSequencer.setRunning(!wordSequencer.isRunning());
        });
        buttons[2] = playButton;

        is = getClass().getClassLoader().getResourceAsStream("images/next_sentence_button.png");
        try {
            bf = ImageIO.read(is);
        } catch (IOException ex) {
            System.out.println("Couldn't load next_sentence_button.png");
        }
        JButton nextButton = new JButton(new ImageIcon(bf));
        nextButton.addActionListener(e -> {
            wordSequencer.setRunning(false);
            bigWord.setText(wordSequencer.nextSentenceBeginning());
        });
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
        openFileItem.addActionListener(e -> {
            wordSequencer.setRunning(false);
            String path = JOptionPane.showInputDialog(frame, "Input full path to file");
            if (path == null) return;
            boolean valid = wordSequencer.openPath(path);
            if (valid) bigWord.setText(wordSequencer.toBeginningOfText());
        });

        menuBar.add(openFileItem);

        JMenuItem pasteTextItem = new JMenuItem("Paste Text");
        pasteTextItem.addActionListener(e -> {
            wordSequencer.setRunning(false);
            String input = JOptionPane.showInputDialog(frame, "Paste text below");
            if (input == null) return;
            ArrayList<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(input.split(" ")));
            wordSequencer.setList(list);
            bigWord.setText(wordSequencer.toBeginningOfText());
        });

        menuBar.add(pasteTextItem);

        JMenuItem statisticsItem = new JMenuItem("Statistics");
        statisticsItem.addActionListener(e -> {
            wordSequencer.setRunning(false);
            JOptionPane.showMessageDialog(frame, wordSequencer.getStatistics(), "Statistics", JOptionPane.INFORMATION_MESSAGE);
        });

        menuBar.add(statisticsItem);

        return menuBar;
    }
}
