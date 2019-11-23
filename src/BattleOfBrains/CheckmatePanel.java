package BattleOfBrains;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class CheckmatePanel {

    private JFrame GameOver = new JFrame("Game over");
    private JButton Exit = new JButton("Exit");
    
    private String s = "Game draw";

    public CheckmatePanel(int GameResult) {

        if (GameResult == 1) {
            s = "White wins by Checkmate";
        }
        if (GameResult == 2) {
            s = "Black wins by Checkmate";
        }

        GameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);

        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(null);

        GameOver.setSize(300, 200);
        GameOver.setLocationByPlatform(false);
        GameOver.setLocation(530, 280);
        GameOver.setVisible(true);
        GameOver.setResizable(false);

        Exit = new JButton("Exit");
        Exit.setSize(80, 25);
        Exit.setLocation(110, 95);
        Exit.addActionListener((ActionEvent e)
                -> {
            GameOver.dispose();

        });

        contentPane.add(Exit);

        JLabel Winner = new JLabel(s, JLabel.CENTER);

        Winner.setSize(300, 30);
        Winner.setLocation(0, 35);
        Winner.setForeground(Color.WHITE);

        Winner.setFont(new Font("Verdana", 1, 15));

        Winner.setVisible(true);
        contentPane.add(Winner);

        GameOver.setContentPane(contentPane);
    }
}