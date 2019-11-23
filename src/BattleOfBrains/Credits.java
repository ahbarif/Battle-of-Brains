package BattleOfBrains;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Credits extends javax.swing.JFrame {

    JFrame frame = new JFrame("Credits");
    JLabel back = new JLabel();
    JLabel jlabel = new JLabel();
    ImageIcon image = new ImageIcon("Resources\\credits.jpg");
    JPanel contentPane = new JPanel();

    public Credits() {

        frame.getContentPane().removeAll();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(705, 485);
        frame.setResizable(false);

        back.setText("Back");
        back.setFont(new Font("Comic Sans MS", 3, 17));
        back.setBounds(615, 410, 50, 25);

        back.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

                frame.dispose();
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
                back.setFont(new Font("Comic Sans MS", 3, 20));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setFont(new Font("Comic Sans MS", 3, 17));
            }
        });

        jlabel.setIcon(image);

        contentPane.setOpaque(true);
        contentPane.setLayout(null);
        contentPane.add(back);
        contentPane.add(jlabel);
        jlabel.setBounds(0, -10, 700, 470);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
        frame.setLocation(300, 100);
    }

}
