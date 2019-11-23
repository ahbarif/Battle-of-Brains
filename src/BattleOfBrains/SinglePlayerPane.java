package BattleOfBrains;

import static BattleOfBrains.Audio_Player.menuMusic;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class SinglePlayerPane {

 
    private JFrame gameFrame = new JFrame("Battle of Brains");
    private int previous, PlayerTurn;
    private int tmp[] = {};
    private boolean GameOver;
    
    private char[][] grid;
    
    private Music Move;

    private final JButton[][] squares = new JButton[8][8];

    private GameState state = new GameState();
    private KingCastle Castle = new KingCastle();
    private AI_Brain Kasparov = new AI_Brain();

    private MyGraphics graphicsUtil = new MyGraphics();
    private MoveGenerator generator = new MoveGenerator();


    public SinglePlayerPane() throws InterruptedException {

        Move = new Music("Resources\\aaa.wav");
 
        menuMusic.closeSong();
        Window.hint = true;
        gameFrame = graphicsUtil.CreateGamePane();
        gameFrame.setTitle("Battle of Brains");
        GameOver = false;
        previous = -1;
        grid = new Board().array;
        PlayerTurn = 1;

        JPanel panel = new JPanel();

        Dimension expectedDimension = new Dimension(520, 520);

        panel.setPreferredSize(expectedDimension);
        panel.setMaximumSize(new Dimension(520, 520));
        panel.setMinimumSize(expectedDimension);

        panel.setLayout(new GridLayout(8, 8));

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonHandler buttonHandler = new ButtonHandler();
       
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

       
        JTextArea ta;
        JTextField tf;

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

        pp.setBounds(640, 190, 190, 250);
        gameFrame.add(pp);
        gameFrame.add(tf);
        JButton Send = new JButton("SEND");
        Send.setFont(new java.awt.Font("Verdana", 1, 9));
        Send.setBounds(770, 495, 60, 20);
        gameFrame.add(Send);
        Send.setVisible(true);

        String AI_chat[] = {"Cool!", "Hey!", "Watch me!", "ha ha ha!", "What's up?", "lol", "treaaat", "ekhon to bolbai", "via nyc apni", "eto dukkho keno gebone :'("};

        Random r = new Random();
        Send.addActionListener((ActionEvent e)
                -> {
            if ("".equals(tf.getText())) {
                return;
            }
            ta.append("You :  " + tf.getText() + "\n\n");
            String strr = AI_chat[r.nextInt(10)];
            ta.append("AI   :   " + strr + "\n\n");       
            tf.setText("");
        });

        gameFrame.setLayout(null);
        panel.setBounds(70, 80, 520, 520);

        gameFrame.getContentPane().setBackground(new Color(237, 204, 150));

        gameFrame.setSize(new Dimension(870, 720));
        gameFrame.add(panel);

        setupTable();

        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        ta.append("AI   :   " + "Best of luck!" + "\n\n");
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

    private void setupTable() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setIcon(new ImageIcon(graphicsUtil.getImage(i, j, grid)));
            }

        }
    }

    void player2() throws InterruptedException {
        Thread.sleep(50);
        char p[][] = grid;

        Move x = Kasparov.findBestMove(p);

        if (x.fromX + x.fromY + x.toX + x.toY == -4) {

            checkGameState(2);
        } else {
        //     Move.playSong();
            MovePiece(x.fromX, x.fromY, x.toX, x.toY);
        }
        PlayerTurn = 1;
        previous = -1;
        memset(tmp);

    }

    public void MovePiece(int fromX, int fromY, int toX, int toY) {

       
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

        if (grid[toX][toY] >= 'a' && grid[toX][toY] <= 'z') {
            checkGameState(1);

        } else {
             Move.playSong();
            checkGameState(2);
        }

        pawnPromotion(toX, toY);
        setupTable();

        Castle.setGrid(grid);
        Castle.setFlags();

    }

    
    private class ButtonHandler implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {
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
                          
                            continue;
                        }

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
                                    MovePiece(fromX, fromY, i, j);
                                    try {
                                        player2();
                                    } catch (InterruptedException ex) {
                                    }

                                } else if (grid[fromX][fromY] == 'X' && Castle.whiteRightCastle() == true) {
                                    MovePiece(fromX, fromY, i, j);
                                    try {
                                        player2();
                                    } catch (InterruptedException ex) {
                                    }

                                } else if (grid[fromX][fromY] == 'X' && Castle.whiteLeftCastle() == true) {
                                    MovePiece(fromX, fromY, i, j);
                                    try {
                                        player2();
                                    } catch (InterruptedException ex) {
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
                                    MovePiece(fromX, fromY, i, j);
                                    try {
                                        player2();
                                    } catch (InterruptedException ex) {
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

    public int[] memset(int a[]) {
        int b[] = {-1};
        a = b;
        return a;
    }

    private boolean isContains(int a[], int key) {
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

   
    public static void main(String[] args) throws InterruptedException {

        SinglePlayerPane xyz = new SinglePlayerPane();

    }
}
