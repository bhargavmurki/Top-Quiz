package bmurki.finalproject.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This class represents the user interface for displaying user scores.
 * It includes a table for displaying scores and a button to start the quiz again.
 */
public class ScoreUI extends JPanel {

    private JLabel lblScore;
    private int score;
    private JLabel lblError;
    private ArrayList<String[]> scoresDict;

    /**
     * Initializes the score panel.
     */
    private void ScorePanel() {
        lblScore = null;
        score = 0;
    }

    /**
     * Constructor for the ScoreUI class.
     * @param username the username of the current user
     */
    public ScoreUI(String username) {
        loadScores(username);

        setPreferredSize(new Dimension(550, 500));

        JPanel scorePanel = new JPanel();
        lblScore = new JLabel("Score : 0", SwingConstants.LEFT);
        lblScore.setFont(new Font("Times New Roman", Font.BOLD, 25));
        JTable table = new JTable();
        table.setBounds(30, 40, 200, 300);
        table.setVisible(true);

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells are non-editable
                return false;
            }
        };

        tableModel.addColumn("Username", new Object[]{"Username"});
        tableModel.addColumn("Geography", new Object[]{"Geography"});
        tableModel.addColumn("Science", new Object[]{"Science"});
        tableModel.addColumn("Entertainment", new Object[]{"Entertainment"});

        for (int i = 0; i < scoresDict.size(); i++) {
            String[] temp = scoresDict.get(i);
            tableModel.addRow(temp);
        }

        table.setModel(tableModel);
        add(table);

        scorePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnPlayAgain = new JButton("");
        btnPlayAgain.setIcon(new ImageIcon("./Resources/LayoutImages/back.png"));
        btnPlayAgain.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnPlayAgain.setHorizontalTextPosition(SwingConstants.CENTER);
        btnPlayAgain.setBorderPainted(false);
        btnPlayAgain.setContentAreaFilled(false);
        btnPlayAgain.setFont(new Font("Times New Roman", Font.BOLD, 15));
        btnPlayAgain.setForeground(new Color(0, 0, 139));
        btnPlayAgain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Start the quiz again
                if (e.getSource() == btnPlayAgain) {
                    Frame[] fr = JFrame.getFrames();
                    for (Frame f : fr) {
                        f.dispose();
                    }
                    new MainFrameUI();
                }
            }
        });

        scorePanel.add(btnPlayAgain);
        add(scorePanel);
    }

    /**
     * Loads user scores from a file named userScore.txt.
     * @param username the username of the current user
     */
    public void loadScores(String username) {
        File file = new File("userScore.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String data;
            scoresDict = new ArrayList<String[]>();
            while ((data = reader.readLine()) != null) {
                String[] individualUserScores = data.split(" ");
                String tempUsername = individualUserScores[0
                        ];
                if (tempUsername.equals(username)) {
                    String[] userScoresArray = new String[4];
                    userScoresArray[0] = individualUserScores[0];
                    userScoresArray[1] = Objects.requireNonNullElse(individualUserScores[1], "0");
                    userScoresArray[2] = Objects.requireNonNullElse(individualUserScores[2], "0");
                    userScoresArray[3] = Objects.requireNonNullElse(individualUserScores[3], "0");
                    System.out.println(Arrays.toString(userScoresArray));
                    scoresDict.add(userScoresArray);
                }
            }
            if (scoresDict.size() == 0) {
                lblError = new JLabel();
                lblError.setText("Score Not Found");//add lblError to layout
                lblError.setForeground(Color.RED);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getScore()
    {
        return score;
    }
}