package BattleOfBrains;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Server {

    private JFrame gameFrame = new JFrame("Battle of Brains");
    private int previous;
    private int tmp[] = {};
    private boolean GameOver;
    private int PlayerTurn;
    private char[][] grid;

    public static ServerSocket ss;
    public static Socket s;
    public static DataInputStream din;
    public static DataOutputStream dout;

    private String PlayerName, ip;
    private int port;

    private final JButton[][] squares = new JButton[8][8];

    private JTextArea ta;
    private JTextField tf;
    private GameState state = new GameState();
    private KingCastle Castle = new KingCastle();

    private MyGraphics graphicsUtil = new MyGraphics();
    private MoveGenerator generator = new MoveGenerator();
    private Music Move = new Music("Resources\\aaa.wav");

    public Server(String PlayerName, String ip, int port) throws IOException {
        this.PlayerName = PlayerName;
        this.ip = ip;
        this.port = port;

        Window.Menu = false;
        System.out.println("Server START......");
        try {
            ss = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        s = ss.accept();
        System.out.println("Client Connected");
        System.out.println("This is server");

        din = new DataInputStream(s.getInputStream());

        dout = new DataOutputStream(s.getOutputStream());

        initComponents();

        System.out.println("****");

    }

    public Server() throws IOException {

    }

    public void initComponents() {

        gameFrame = graphicsUtil.CreateGamePane();
        gameFrame.setTitle("Server");
        GameOver = false;
        previous = -1;
        grid = new Board().array;
        PlayerTurn = 1;
        Window.hint = true;

        JPanel panel = new JPanel();

        Dimension expectedDimension = new Dimension(520, 520);

        panel.setPreferredSize(expectedDimension);
        panel.setMaximumSize(new Dimension(520, 520));
        panel.setMinimumSize(expectedDimension);

        panel.setLayout(new GridLayout(8, 8));

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonHandler buttonHandler = new ButtonHandler();
        //   -------------------------------    Adding buttons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if ((i + j) % 2 != 0) {
                    squares[i][j] = new JButton(new ImageIcon("Resources\\DarkCell.png"));
                } else {
                    squares[i][j] = new JButton(new ImageIcon("Resources\\LightCell.png"));
                }

                gameFrame.getContentPane().add(squares[i][j]);

                panel.add(squares[i][j]);
                squares[i][j].addActionListener(buttonHandler);

            }
        }

        ta = new JTextArea(15, 25);
        tf = new JTextField(20);

        ta.setColumns(5);
        ta.setEditable(false);
        ta.append("             Chat Room\n\n");
        ta.setFont(new Font("Verdana", 1, 11));

        ta.setBackground(new Color(206, 206, 206));
        ta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        ta.setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));

        JScrollPane pp = new JScrollPane((ta));
        tf.setBounds(640, 465, 190, 25);
        tf.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        pp.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        pp.setBounds(640, 150, 190, 300);
        gameFrame.add(pp);
        gameFrame.add(tf);
        JButton Send = new JButton("SEND");
        Send.setFont(new java.awt.Font("Verdana", 1, 9));
        Send.setBounds(770, 495, 60, 20);
        gameFrame.add(Send);
        Send.setVisible(true);
        Send.addActionListener((ActionEvent e)
                -> {
            String s1 = " " + tf.getText();
            tf.setText("");

            if (s1.length() > 0) {
                try {
                    ta.append(PlayerName + " :" + s1 + "\n");
                    dout.writeUTF(PlayerName + " :" + s1);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        gameFrame.setLayout(null);
        panel.setBounds(70, 65, 520, 520);

        gameFrame.getContentPane().setBackground(new Color(237, 204, 150));

        gameFrame.setSize(new Dimension(870, 720));
        gameFrame.add(panel);

        setupTable();

        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    void resetBorder() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                squares[x][y].setBorder(UIManager.getBorder("Button.border"));
            }
        }
    }

    public void pawnPromotion(int toX, int toY) {
        if (toX == 0 && grid[toX][toY] == 'P') {
            grid[toX][toY] = 'Q';
        }
        if (toX == 7 && grid[toX][toY] == 'p') {
            grid[toX][toY] = 'q';
        }
    }

    void resetCell(int i, int j) {
        if ((i + j) % 2 == 0) {
            squares[i][j].setIcon(new ImageIcon("Resources\\LightCell.png"));
        } else {
            squares[i][j].setIcon(new ImageIcon("Resources\\DarkCell.png"));
        }
    }

    private void checkGameState(int x) {
        if (GameOver == true) {
            return;
        }
        state.setGrid(grid, x);

        switch (state.CurrentState()) {
            case 1: {
                GameOver = true;
                CheckmatePanel gameover = new CheckmatePanel(1);
                break;
            }
            case 2: {
                GameOver = true;
                CheckmatePanel gameover = new CheckmatePanel(2);
                break;
            }
            case 3: {
                GameOver = true;
                CheckmatePanel gameover = new CheckmatePanel(3);
                break;
            }
            default:
                break;
        }
    }

    private void setupTable() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setIcon(new ImageIcon(graphicsUtil.getImage(i, j, grid)));
            }

        }
    }

    public void MoveOpponentPiece(String s) {

        if (GameOver == false) {
            Move.playSong();
        }
        if (PlayerTurn == 1) {
            return;
        }
        PlayerTurn = 1;

        int fromX = s.charAt(1) - 48;
        int fromY = s.charAt(2) - 48;
        int toX = s.charAt(3) - 48;
        int toY = s.charAt(4) - 48;

        resetCell(fromX, fromY);
        grid[toX][toY] = grid[fromX][fromY];

        if (fromX == 0 && fromY == 4 && toX == 0 && toY == 6 && grid[0][6] == 'x') {
            grid[0][5] = 'r';
            grid[0][7] = '.';
        }
        if (fromX == 0 && fromY == 4 && toX == 0 && toY == 2 && grid[0][2] == 'x') {
            grid[0][3] = 'r';
            grid[0][0] = '.';
        }

        grid[fromX][fromY] = '.';
        checkGameState(1);
        PlayerTurn = 1;

        pawnPromotion(toX, toY);

        setupTable();
        resetBorder();

    }

    public void MovePiece(int fromX, int fromY, int toX, int toY) throws IOException {

        if (GameOver == false) {
            Move.playSong();
        }
        PlayerTurn = 2;

        dout.writeUTF("+" + fromX + fromY + toX + toY);

        dout.flush();

        resetCell(fromX, fromY);

        grid[toX][toY] = grid[fromX][fromY];

        grid[fromX][fromY] = '.';

        if (fromX == 7 && fromY == 4 && toX == 7 && toY == 6 && grid[toX][toY] == 'X') {
            grid[7][5] = 'R';
            grid[7][7] = '.';
        }

        if (fromX == 7 && fromY == 4 && toX == 7 && toY == 2 && grid[toX][toY] == 'X') {
            grid[7][3] = 'R';
            grid[7][0] = '.';
        }

        generator.setGrid(grid);
        generator.setTurn(PlayerTurn);
        checkGameState(2);
        pawnPromotion(toX, toY);
        setupTable();

        Castle.setGrid(grid);
        Castle.setFlags();
        resetBorder();

    }

    private class ButtonHandler implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
            if (PlayerTurn == 2) {
                return;
            }
            Object source = e.getSource();
            if (GameOver == true) {
                return;
            }

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (source == squares[i][j]) {

                        generator.setGrid(grid);
                        generator.setTurn(PlayerTurn);
                        Castle.setGrid(grid);

                        System.out.println(grid[i][j]);

                        if (grid[i][j] >= 'a' && previous == -1) {
                            System.out.println("1");
                            continue;
                        }

                        System.out.println(previous + " = prev");

                        if (previous == -1) {
                            memset(tmp);
                            if (grid[i][j] == '.') {
                                resetBorder();

                            } else {
                                resetBorder();
                                tmp = highlightLegalMoves(generator.lol(i, j), i, j);
                                previous = i * 8 + j;
                            }
                        } else {
                            int fromX = previous / 8;
                            int fromY = previous % 8;
                            int cell = i * 8 + j;
                            if (grid[i][j] == '.') {

                                if (isContains(tmp, cell) == true) {
                                    try {
                                        MovePiece(fromX, fromY, i, j);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                } else if (grid[fromX][fromY] == 'X' && Castle.whiteRightCastle() == true) {
                                    try {
                                        MovePiece(fromX, fromY, i, j);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                } else if (grid[fromX][fromY] == 'X' && Castle.whiteLeftCastle() == true) {
                                    try {
                                        MovePiece(fromX, fromY, i, j);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }

                                previous = -1;
                                resetBorder();
                                memset(tmp);

                            } else if (isSameColor(i, j, fromX, fromY)) {
                                resetBorder();
                                tmp = highlightLegalMoves(generator.lol(i, j), i, j);
                                previous = i * 8 + j;
                            } else {
                                if (isContains(tmp, cell) == true) {
                                    try {
                                        MovePiece(fromX, fromY, i, j);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                }

                                previous = -1;
                                resetBorder();
                                memset(tmp);
                            }
                        }
                    }

                }
            }

        }

    }

    String str = "";

    class Reciever extends Thread {

        @Override
        public void run() {
            while (true) {

                try {
                    str = din.readUTF();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (str.charAt(0) != '+') {
                    ta.append(str + "\n");
                } else {
                    MoveOpponentPiece(str);
                }

                try {
                    dout.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public int[] memset(int a[]) {
        int b[] = {-1};
        a = b;
        return a;
    }

    public boolean isContains(int a[], int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
                return true;
            }
        }
        return false;
    }

    public boolean isSameColor(int i, int j, int x, int y) {
        if (grid[i][j] >= 'a' && grid[i][j] <= 'z' && grid[x][y] >= 'a' && grid[x][y] <= 'z') {
            return true;
        } else {
            return grid[i][j] >= 'A' && grid[i][j] <= 'Z' && grid[x][y] >= 'A' && grid[x][y] <= 'Z';
        }
    }

    private int[] highlightLegalMoves(int a[], int i, int j) {

        if (Window.hint == true) {
            for (int k = 0; k < a.length; k++) {

                if (a[k] == -1) {
                    continue;
                }

                if (grid[a[k] / 8][a[k] % 8] != '.') {
                    squares[a[k] / 8][a[k] % 8].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                } else {
                    squares[a[k] / 8][a[k] % 8].setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }
            }

            if (grid[i][j] == 'X' && Castle.whiteRightCastle() == true) {

                squares[7][6].setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
            }

            if (grid[i][j] == 'X' && Castle.whiteLeftCastle() == true) {

                squares[7][2].setBorder(BorderFactory.createLineBorder(Color.CYAN, 3));
            }
        }
        return a;
    }

}
