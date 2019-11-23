package BattleOfBrains;

public class MoveGenerator {

    private char[][] grid;
    int PlayerTurn;

     int underWhiteAttack[] = {};
     int underBlackAttack[] = {};

    MoveGenerator() {}

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public void setTurn(int PlayerTurn) {
        this.PlayerTurn = PlayerTurn;
    }

    public boolean KingUnderCheck(int player) {
        if (player == 1) {
            generateBlackAttackedCells();
            int pos = findKing('X');
            for (int i : underBlackAttack) {
                if (i == pos) {
                    return true;
                }
            }
            return false;
        } else {
            generateWhiteAttackedCells();
            int pos = findKing('x');
            for (int i : underWhiteAttack) {
                if (i == pos) {
                    return true;
                }
            }
            return false;
        }

    }

    public boolean validate(int a, int b, int x, int y, int turn) {

        char old = grid[x][y];
        char latest = grid[a][b];
        boolean flag = true;

        grid[x][y] = '.';
        grid[a][b] = old;

        if (turn == 2) {
            generateWhiteAttackedCells();
            int kingPos = findKing('x');
            for (int auto : underWhiteAttack) {
                if (auto == kingPos) {
                    flag = false;
                }
            }

        } else {
            generateBlackAttackedCells();
            int kingPos = findKing('X');
            for (int auto : underBlackAttack) {
                if (auto == kingPos) {
                    flag = false;
                }
            }
        }

        grid[x][y] = old;
        grid[a][b] = latest;
        return flag;
    }

    public int findKing(char p) {
        int tmp = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == p) {
                    tmp = i * 8 + j;
                    break;
                }
            }
        }

        return tmp;
    }

    public void generateWhiteAttackedCells() {

        underWhiteAttack = getMoves(1);

    }

    public void generateBlackAttackedCells() {

        underBlackAttack = getMoves(2);

    }

    public int[] getBlack() {
        return underBlackAttack;
    }

    public int[] getWhite() {
        return underWhiteAttack;
    }

    int[] lol(int i, int j) {

        int a[] = {};

        if (grid[i][j] == 'K' || grid[i][j] == 'k') {

            Knight K = (grid[i][j] == 'k' ? new Knight("black", i, j, grid) : new Knight("white", i, j, grid));

            a = K.getLegalMoves();
        }

        if (grid[i][j] == 'B' || grid[i][j] == 'b') {

            Bishop B = (grid[i][j] == 'b' ? new Bishop("black", i, j, grid) : new Bishop("white", i, j, grid));

            a = B.getLegalMoves();
        }

        if (grid[i][j] == 'R' || grid[i][j] == 'r') {
            Rook k1;
            if (grid[i][j] == 'r') {
                k1 = new Rook("black", i, j, grid);
            } else {
                k1 = new Rook("white", i, j, grid);
            }
            a = k1.getLegalMoves();
        }

        if (grid[i][j] == 'q' || grid[i][j] == 'Q') {
            Queen k1;
            if (grid[i][j] == 'q') {
                k1 = new Queen("black", i, j, grid);
            } else {
                k1 = new Queen("white", i, j, grid);
            }
            a = k1.getLegalMoves();
        }

        if (grid[i][j] == 'x' || grid[i][j] == 'X') {
            King k1;
            if (grid[i][j] == 'x') {
                k1 = new King("black", i, j, grid);
            } else {
                k1 = new King("white", i, j, grid);
            }
            a = k1.getLegalMoves();
        }

        if (grid[i][j] == 'p' || grid[i][j] == 'P') {
            Pawn k1;
            boolean f = true;
            if (i == 1 || i == 6) {
                f = false;
            }
            if (grid[i][j] == 'p') {
                k1 = new Pawn("black", i, j, grid, f);
            } else {
                k1 = new Pawn("white", i, j, grid, f);
            }
            a = k1.getLegalMoves();
        }

        for (int k = 0; k < a.length; k++) {

            int x = a[k] / 8;
            int y = a[k] % 8;

            if (validate(x, y, i, j, PlayerTurn) == false) {
                a[k] = -1;
            }

        }

        return a;
    }

    public int[] getMoves(int turn) {
        int a[] = {};

        int cnt = 0;
        int t[] = new int[1000];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (grid[i][j] == '.') {
                    continue;
                }

                if (turn == 2 && grid[i][j] <= 'Z') {
                    continue;
                }
                if (turn == 1 && grid[i][j] >= 'a') {
                    continue;
                }

                if (grid[i][j] == 'K' || grid[i][j] == 'k') {
                    Knight k1;

                    if (turn == 2) {
                        k1 = new Knight("black", i, j, grid);
                    } else {
                        k1 = new Knight("white", i, j, grid);
                    }

                    a = k1.getLegalMoves();
                }

                if (grid[i][j] == 'B' || grid[i][j] == 'b') {
                    Bishop k1;

                    if (turn == 2) {
                        k1 = new Bishop("black", i, j, grid);
                    } else {
                        k1 = new Bishop("white", i, j, grid);
                    }
                    a = k1.getLegalMoves();
                }

                if (grid[i][j] == 'R' || grid[i][j] == 'r') {
                    Rook k1;

                    if (turn == 2) {
                        k1 = new Rook("black", i, j, grid);
                    } else {
                        k1 = new Rook("white", i, j, grid);
                    }

                    a = k1.getLegalMoves();
                }

                if (grid[i][j] == 'q' || grid[i][j] == 'Q') {
                    Queen k1;
                    if (turn == 2) {
                        k1 = new Queen("black", i, j, grid);
                    } else {
                        k1 = new Queen("white", i, j, grid);
                    }
                    a = k1.getLegalMoves();
                }

                if (grid[i][j] == 'x' || grid[i][j] == 'X') {
                    King k1;
                    if (turn == 2) {
                        k1 = new King("black", i, j, grid);
                    } else {
                        k1 = new King("white", i, j, grid);
                    }
                    a = k1.getLegalMoves();
                }

                if (grid[i][j] == 'p' || grid[i][j] == 'P') {
                    Pawn k1;
                    boolean f = true;
                    if (i == 1 || i == 6) {
                        f = false;
                    }
                    if (turn == 2) {
                        k1 = new Pawn("black", i, j, grid, f);
                    } else {
                        k1 = new Pawn("white", i, j, grid, f);
                    }
                    a = k1.getLegalMoves();
                }

                for (int x : a) {
                    t[cnt++] = x;
                }
            }

        }

        int res[] = new int[cnt];

        System.arraycopy(t, 0, res, 0, cnt);

        return res;
    }

}
