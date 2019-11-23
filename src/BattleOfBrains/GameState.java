package BattleOfBrains;

public class GameState {

    private char[][] grid;
    private int PlayerTurn;
    private final MoveGenerator gen = new MoveGenerator();

    public GameState() {
    }

    public void setGrid(char[][] grid, int PlayerTurn) {
        this.grid = grid;
        this.PlayerTurn = PlayerTurn;
    }

    public int CurrentState() {

        int x = isCheckMate();

        if (x == 0) {
            return (isDraw() ? 3 : x);
        } else {

            if (x == 1) {
                if (gen.KingUnderCheck(2) == false) {
                    x = 3;
                }
            }

            if (x == 2) {
                if (gen.KingUnderCheck(1) == false) {
                    x = 3;
                }
            }
            return x;
        }
    }

    public boolean isWhiteWins() {
        gen.setGrid(grid);
        gen.PlayerTurn = PlayerTurn;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] >= 'a') {
                    int w[] = gen.lol(i, j);
                    for (int x = 0; x < w.length; x++) {
                        if (w[x] != -1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean isBlackWins() {

        gen.setGrid(grid);
        gen.PlayerTurn = PlayerTurn;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] <= 'Z' && grid[i][j] >= 'A') {
                    int w[] = gen.lol(i, j);
                    for (int x = 0; x < w.length; x++) {
                        if (w[x] != -1) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public int isCheckMate() {

        boolean whiteWins = false;
        boolean blackWins = false;

        whiteWins = isWhiteWins();
        blackWins = isBlackWins();

        if (whiteWins == true) {
            return 1;
        }
        if (blackWins == true) {

            return 2;
        }

        return 0;

    }

    public boolean isDraw() {
        boolean flag = true;

        int BlackCount = 0;
        int WhiteCount = 0;
        int wb = 0, bb = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] >= 'A' && grid[i][j] <= 'Z') {
                    ++WhiteCount;
                    if (grid[i][j] == 'B') {
                        ++wb;
                    }

                    if (grid[i][j] != 'K' && grid[i][j] != 'X') {
                        flag = false;
                    }
                }

                if (grid[i][j] >= 'a' && grid[i][j] <= 'z') {
                    ++BlackCount;
                    if (grid[i][j] == 'b') {
                        ++bb;
                    }

                    if (grid[i][j] != 'k' && grid[i][j] != 'x') {
                        flag = false;
                    }
                }
            }
        }

        if (WhiteCount == 1 && BlackCount == 1) {
            flag = true;
        }
        if (WhiteCount == 2 && BlackCount == 2 && wb == 1 && bb == 1) {
            flag = true;
        }

        return flag;
    }
}
