package BattleOfBrains;

import static BattleOfBrains.Audio_Player.menuMusic;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameJoinPane {

    JFrame frame = new JFrame("Join Game");

    JButton name = new JButton("Name");
    JButton ip = new JButton("IP");
    JButton port = new JButton("Port");
    JButton back = new JButton("Back");
    JButton start = new JButton("Start");

    JTextField _name = new JTextField();
    JTextField _ip = new JTextField();
    JTextField _port = new JTextField();

    JLabel jlabel = new JLabel();
    ImageIcon icon = new ImageIcon("Resources\\knight.jpg");

    public GameJoinPane() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setOpaque(true);

        frame.setSize(650, 400);
        frame.setLocation(530, 200);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocation(320, 120);

        name = new JButton("Name");
        name.setBounds(130, 105, 75, 30);

        ip = new JButton("IP");
        ip.setBounds(130, 165, 75, 30);

        port = new JButton("Port");
        port.setBounds(130, 225, 75, 30);

        back = new JButton("Back");
        back.setBounds(60, 320, 85, 30);
        back.addActionListener((ActionEvent e)
                -> {
            frame.dispose();
            Window.Menu = true;

        });

        start = new JButton("Join");
        start.setBounds(430, 320, 85, 30);

        start.addActionListener((ActionEvent e)
                -> {

            frame.dispose();
            Window.Menu = false;
            menuMusic.closeSong();

            Client xyz;
            try {
                xyz = new Client(_name.getText(), _ip.getText(), Integer.valueOf(_port.getText()));
                xyz.new Reciever().start();
            } catch (IOException ex) {
                Logger.getLogger(ServerCreatePane.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        _name = new JTextField();
        _name.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        _name.setBounds(240, 105, 200, 30);
        _name.setFont(new Font("Consolas", 3, 17));

        _ip = new JTextField();
        _ip.setBounds(240, 165, 200, 30);
        _ip.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        _ip.setFont(new Font("Consolas", 3, 17));

        _port = new JTextField();
        _port.setBounds(240, 225, 200, 30);
        _port.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        _port.setFont(new Font("Consolas", 3, 17));

        jlabel.setIcon(icon);

        contentPane.setLayout(null);

        contentPane.add(name);
        contentPane.add(ip);
        contentPane.add(port);
        contentPane.add(back);
        contentPane.add(start);

        contentPane.add(_name);
        contentPane.add(_ip);
        contentPane.add(_port);

        contentPane.add(jlabel);
        jlabel.setBounds(0, -20, 650, 405);

        frame.setContentPane(contentPane);
        frame.setVisible(true);

    }

}
