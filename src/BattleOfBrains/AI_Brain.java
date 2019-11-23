package BattleOfBrains;

import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class AI_Brain {

    private final MoveGenerator gene = new MoveGenerator();
    private char[][] grid;
    private int PlayerTurn;
    private final HashMap<Character, Integer> PiecePoint = new HashMap<>();

    char[][] copy() {
        char t[][] = new char[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(grid[i], 0, t[i], 0, 8);
        }

        return t;
    }

    AI_Brain() {
        PiecePoint.put('Q', 9);
        PiecePoint.put('X', 1);
        PiecePoint.put('R', 5);
        PiecePoint.put('K', 3);
        PiecePoint.put('B', 3);
        PiecePoint.put('P', 1);
        PiecePoint.put('q', 9);
        PiecePoint.put('x', 1);
        PiecePoint.put('r', 5);
        PiecePoint.put('k', 3);
        PiecePoint.put('b', 3);
        PiecePoint.put('p', 1);
        PiecePoint.put('.', 0);
    }

    public int evaluate(char[][] ggrid, int x) {

        gene.setGrid(ggrid);
        gene.generateBlackAttackedCells();
        gene.generateWhiteAttackedCells();

        int w[] = gene.getWhite();
        int b[] = gene.getBlack();

        int human_score = 0;

        int pos = gene.findKing('X');

        int computer_score = x;

        if (isContains(b, pos)) {
            computer_score += 2;
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int row = i;
                int col = j;

                if (ggrid[i][j] >= 'A' && ggrid[i][j] <= 'Z') {

                    human_score += PiecePoint.get(ggrid[i][j]);

                    if (ggrid[i][j] == 'P') {
                        if (row < 4) {
                            human_score += (8 - row);
                        }
                        if (col > 4) {
                            human_score -= ((col - 4));
                        } else if (col < 3) {
                            human_score -= ((3 - col));
                        }
                        if (col > 1 && col < 6 && row > 1) {
                            human_score += 2;
                        }
                        if (row == 0) {
                            human_score += 10;
                        }
                    } else if (ggrid[i][j] == 'K' || ggrid[i][j] == 'B') {
                        if (row == 7) {
                            human_score -= 1;
                        }
                        if (col == 0 || col == 7) {
                            human_score -= 1;
                        }
                        if (col > 1 && col < 6 && row > 1 && row < 6) {
                            human_score += 1;
                        }
                    }

                }

            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int row = i;
                int col = j;

                if (ggrid[i][j] >= 'a' && ggrid[i][j] <= 'z') {

                    computer_score += PiecePoint.get(ggrid[i][j]);

                    if (isContains(w, i * 8 + j)) {
                        human_score += PiecePoint.get(ggrid[i][j]);
                    }

                    if (ggrid[i][j] == 'p') {
                        if (row > 3) {
                            computer_score += row;
                        }
                        if (col > 4) {
                            computer_score -= ((col - 4));
                        } else if (col < 3) {
                            computer_score -= ((3 - col));
                        }
                        if (col > 1 && col < 6 && row > 1) {
                            computer_score += 2;
                        }
                        if (row == 7) {
                            computer_score += 9;
                        }
                    } else if (ggrid[i][j] == 'k' || ggrid[i][j] == 'b') {
                        if (row == 0) {
                            computer_score -= 1;
                        }
                        if (col == 0 || col == 7) {
                            computer_score -= 1;
                        }
                        if (col > 1 && col < 6 && row > 1 && row < 6) {
                            computer_score += 1;
                        }
                    }

                }
            }
        }
        return computer_score - human_score;
    }

    private Move bestMove(Vector<Move> v) {

        gene.generateBlackAttackedCells();
        gene.generateWhiteAttackedCells();
        int w[] = gene.getWhite();

        int score = -111111;
        Move best = new Move(-1, -1, -1, -1);

        Move[] all = new Move[200];
        int idx = 0;

        for (Move x : v) {
            int tempScore = 0;
            int fromX = x.fromX;
            int fromY = x.fromY;

            int toX = x.toX;
            int toY = x.toY;

            if (isContains(w, x.toX * 8 + x.toY)) {
                tempScore -= PiecePoint.get(grid[toX][toY]);

            }

            char[][] tmp = copy();

            tmp[toX][toY] = grid[fromX][fromY];
            tmp[fromX][fromY] = '.';

            int cur = evaluate(tmp, tempScore);
            if (cur > score) {
                score = cur;
                idx = 0;
                all[idx++] = x;

            } else if (cur == score) {
                all[idx++] = x;
            }
        }

        Random r = new Random();

        if (idx == 0) {
            return new Move(-1, -1, -1, -1);
        }
        int k = r.nextInt(idx);

        return all[k];
    }

    public boolean isContains(int a[], int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
                return true;
            }
        }
        return false;
    }

    public Move findBestMove(char grid[][]) {

        this.grid = grid;
        gene.setGrid(grid);
        gene.PlayerTurn = 2;

        Vector<Move> allMoves = new Vector<>();
        allMoves.clear();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] >= 'a') {
                    int w[] = gene.lol(i, j);
                    for (int x = 0; x < w.length; x++) {
                        if (w[x] != -1) {
                            allMoves.add(new Move(i, j, w[x] / 8, w[x] % 8));
                        }
                    }
                }
            }
        }

        return bestMove(allMoves);
    }
}