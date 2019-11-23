package BattleOfBrains;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyGraphics {

    MyGraphics() {
    }

    public String getImage(int i, int j, char[][] grid) {

        String s = "Resources\\";
        if (grid[i][j] == '.') {
            return ((i + j) % 2 == 1 ? "Resources\\DarkCell.png" : "Resources\\LightCell.png");
        }
        if ((i + j) % 2 == 1) {

            switch (grid[i][j]) {
                case 'k':
                    return (s + "BlackKnightDark.png");

                case 'K':

                    return (s + "WhiteKnightDark.png");

                case 'B':
                    return (s + "WhiteBishopDark.png");

                case 'b':
                    return (s + "BlackBishopDark.png");

                case 'R':
                    return (s + "WhiteRookDark.png");

                case 'r':
                    return (s + "BlackRookDark.png");

                case 'q':
                    return (s + "BlackQueenDark.png");

                case 'Q':
                    return (s + "WhiteQueenDark.png");

                case 'x':
                    return (s + "BlackKingDark.png");

                case 'X':
                    return (s + "WhiteKingDark.png");

                case 'p':
                    return (s + "BlackPawnDark.png");

                case 'P':
                    return (s + "WhitePawnDark.png");

                default:

            }
        } else {
            switch (grid[i][j]) {
                case 'k':
                    return (s + "BlackKnightLight.png");

                case 'K':
                    return (s + "WhiteKnightLight.png");

                case 'B':
                    return (s + "WhiteBishopLight.png");

                case 'b':
                    return (s + "BlackBishopLight.png");

                case 'R':
                    return (s + "WhiteRookLight.png");

                case 'r':
                    return (s + "BlackRookLight.png");

                case 'q':
                    return (s + "BlackQueenLight.png");

                case 'Q':
                    return (s + "WhiteQueenLight.png");

                case 'x':
                    return (s + "BlackKingLight.png");

                case 'X':
                    return (s + "WhiteKingLight.png");

                case 'p':
                    return (s + "BlackPawnLight.png");

                case 'P':
                    return (s + "WhitePawnLight.png");

                default:

            }
        }
        return "-1";
    }
    
    
    public JFrame CreateGamePane()
    {
        JFrame gameFrame = new JFrame("Battle of Brains");
        
        JLabel Power = new JLabel();
        Power.setIcon(new ImageIcon("Resources\\power1.png"));
        Power.setBounds(640, 550, 47, 47);
        gameFrame.add(Power);
        
        JLabel back = new JLabel();
        back.setIcon(new ImageIcon("Resources\\back1.png"));
        back.setBounds(715, 550, 47, 47);
        gameFrame.add(back);
        
        JLabel hints = new JLabel();
        hints.setIcon(new ImageIcon("Resources\\hint1.png"));
        hints.setBounds(780, 550, 47, 47);
        gameFrame.add(hints);
        
        Power.addMouseListener(new MouseListener() {
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
                  Power.setIcon(new ImageIcon("Resources\\power2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 Power.setIcon(new ImageIcon("Resources\\power1.png"));
            }
        });
        
        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               gameFrame.dispose();
               Window.Menu = true;
            }

            @Override
            public void mousePressed(MouseEvent e) {
              
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                  back.setIcon(new ImageIcon("Resources\\back2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 back.setIcon(new ImageIcon("Resources\\back1.png"));
            }
        });
        
        hints.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Window.hint = Window.hint != true;
                System.out.println("CLicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
              
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                  hints.setIcon(new ImageIcon("Resources\\hint2.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 hints.setIcon(new ImageIcon("Resources\\hint1.png"));
            }
        });
        
        return gameFrame;
    }

}
