package pavelgarmuyev.fastreader.gui;

import javax.swing.*;
import java.awt.*;

public class UserInterface implements Runnable {

    private JFrame frame;

    public UserInterface() {}

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
        BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();

        // JLabel wordCount = new JLabel("Word Count: 1001");
        // wordCount.setAlignmentX(0.0f);
        // container.add(wordCount);
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

        container.add(bigWordPanel);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.X_AXIS));
        controlsPanel.setBackground(Color.WHITE);
        controlsPanel.setOpaque(true);

        ImageIcon rewindImg = new ImageIcon("rewind_button2.png");
        JButton backwardsButton = new JButton(rewindImg);
        backwardsButton.setOpaque(false);
        backwardsButton.setContentAreaFilled(false);
        backwardsButton.setBorderPainted(false);
        controlsPanel.add(backwardsButton);

        container.add(controlsPanel);

        container.add(Box.createVerticalGlue());

        //JLabel speed = new JLabel("Speed: 500 wpm");
        //speed.setAlignmentX(0.0f);
        //container.add(speed);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(50);
        progressBar.setStringPainted(true);
        progressBar.setAlignmentX(0.9f);

        container.add(Box.createVerticalGlue());
        container.add(progressBar);
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
