package BattleOfBrains;

import static BattleOfBrains.Audio_Player.menuMusic;
import static BattleOfBrains.Audio_Player.sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class MainMenu {

    public final JFrame frame;
    private javax.swing.JLabel jLabel1;

    public MainMenu() {
 
        frame = new JFrame("Battle of Brains");
        initComponents();
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        JLabel gameTitle = new JLabel();
        gameTitle.setIcon(new ImageIcon("Resources\\t6.png"));
        gameTitle.setBounds(100, 30, 620, 150);
        frame.add(gameTitle);

        JLabel singlePlayer = new JLabel("Single Player");
        singlePlayer.setFont(new Font("OCR A Std", 3, 17));

        JLabel createGame = new JLabel("Create Game");
        createGame.setFont(new Font("OCR A Std", 3, 17));
        createGame.setForeground(new Color(26, 3, 129));

        JLabel joinGame = new JLabel("Join Game");
        joinGame.setFont(new Font("OCR A Std", 3, 17));
        joinGame.setForeground(new Color(26, 3, 129));

        JLabel credits = new JLabel("Credits");
        credits.setFont(new Font("OCR A Std", 3, 17));
        credits.setForeground(new Color(26, 3, 129));

        JLabel exit = new JLabel("Exit");
        exit.setFont(new Font("OCR A Std", 3, 17));
        exit.setForeground(new Color(26, 3, 129));

        singlePlayer.setBounds(600, 200, 180, 50);
        createGame.setBounds(600, 235, 180, 50);
        joinGame.setBounds(600, 270, 180, 50);
        credits.setBounds(600, 305, 180, 50);
        exit.setBounds(600, 340, 180, 50);
        singlePlayer.setForeground(new Color(26, 3, 129));

        JLabel soundOption = new JLabel();
        soundOption.setIcon(new ImageIcon("Resources\\s1.png"));
        soundOption.setBounds(10, 445, 50, 50);

        frame.add(singlePlayer);
        frame.add(createGame);
        frame.add(joinGame);
        frame.add(credits);
        frame.add(exit);
        frame.add(soundOption);

        gameTitle.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gameTitle.setIcon(new ImageIcon("Resources\\t5.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gameTitle.setIcon(new ImageIcon("Resources\\t6.png"));
            }
        });

        singlePlayer.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Window.Menu = false;
                menuMusic.closeSong();

                try {
                    SinglePlayerPane Pane = new SinglePlayerPane();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                singlePlayer.setBounds(600, 200, 200, 50);
                singlePlayer.setFont(new Font("OCR A Std", 3, 20));
                singlePlayer.setForeground(new Color(33, 99, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                singlePlayer.setFont(new Font("OCR A Std", 3, 17));
                singlePlayer.setBounds(600, 200, 180, 50);
                singlePlayer.setForeground(new Color(26, 3, 129));
            }
        });

        createGame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Window.Menu = false;
                ServerCreatePane Pane = new ServerCreatePane();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                createGame.setBounds(600, 235, 200, 50);
                createGame.setFont(new Font("OCR A Std", 3, 20));
                createGame.setForeground(new Color(33, 99, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                createGame.setFont(new Font("OCR A Std", 3, 17));
                createGame.setBounds(600, 235, 180, 50);
                createGame.setForeground(new Color(26, 3, 129));
            }
        });

        joinGame.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Window.Menu = false;
                GameJoinPane Pane = new GameJoinPane();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                joinGame.setBounds(600, 270, 200, 50);
                joinGame.setFont(new Font("OCR A Std", 3, 20));
                joinGame.setForeground(new Color(33, 99, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                joinGame.setFont(new Font("OCR A Std", 3, 17));
                joinGame.setBounds(600, 270, 180, 50);
                joinGame.setForeground(new Color(26, 3, 129));
            }
        });

        credits.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                Window.Menu = false;
                Credits C = new Credits();

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                credits.setBounds(600, 305, 200, 50);
                credits.setFont(new Font("OCR A Std", 3, 20));
                credits.setForeground(new Color(33, 99, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                credits.setFont(new Font("OCR A Std", 3, 17));
                credits.setBounds(600, 305, 180, 50);
                credits.setForeground(new Color(26, 3, 129));
            }
        });

        exit.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBounds(600, 340, 200, 50);
                exit.setFont(new Font("OCR A Std", 3, 20));
                exit.setForeground(new Color(33, 99, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setFont(new Font("OCR A Std", 3, 17));
                exit.setBounds(600, 340, 180, 50);
                exit.setForeground(new Color(26, 3, 129));
            }
        });

        ImageIcon off = new ImageIcon("Resources\\s2.png");
        ImageIcon on = new ImageIcon("Resources\\s1.png");

        soundOption.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (soundOption.getIcon() == off) {
                    soundOption.setIcon(on);
                    sound = true;
                    menuMusic.playSong();
                } else {
                    soundOption.setIcon(off);
                    sound = false;
                    menuMusic.closeSong();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new java.awt.Dimension(825, 520));
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);

        jLabel1.setIcon(new ImageIcon(("Resources\\4.jpg")));
        frame.getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -5, 825, 520);
        frame.add(jLabel1);

        frame.pack();
    }

}

public class BattleOfBrains {

    public static void main(String[] args) throws InterruptedException {

        MainMenu x = new MainMenu();
        x.frame.setVisible(true);

        x.frame.setLocation(300, 100);

        while (true) {
            if (Window.Menu) {

                Thread.sleep(100);
                x.frame.setVisible(true);
                if (menuMusic.state == false && sound == true) {
                    menuMusic.playSong();
                }

            } else {
                x.frame.setVisible(false);
            }
        }

    }

}