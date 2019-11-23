package BattleOfBrains;

public class KingCastle {

    private char[][] grid;

    private boolean whiteKingMoved, whiteRookLeft, whiteRookRight;

    private boolean blackKingMoved, blackRookLeft, blackRookRight;

    private final MoveGenerator Mygenerator = new MoveGenerator();

    public KingCastle() {
        whiteKingMoved = false;
        whiteRookLeft = false;
        whiteRookRight = false;
        blackKingMoved = false;
        blackRookLeft = false;
        blackRookRight = false;
    }

    public void setGrid(char[][] grid) {
        this.grid = grid;
    }

    public void setFlags() {
        if (grid[7][0] != 'R') {
            whiteRookLeft = true;
        }
        if (grid[7][7] != 'R') {
            whiteRookRight = true;
        }
        if (grid[7][4] != 'X') {
            whiteKingMoved = true;
        }

        if (grid[0][0] != 'r') {
            blackRookLeft = true;
        }
        if (grid[0][7] != 'r') {
            blackRookRight = true;
        }
        if (grid[0][4] != 'x') {
            blackKingMoved = true;
        }
    }

    public boolean whiteRightCastle() {
        if (whiteKingMoved == false && whiteRookRight == false) {
            if (grid[7][5] == '.' && grid[7][6] == '.') {
                Mygenerator.setGrid(grid);
                Mygenerator.generateBlackAttackedCells();
                if (!isContains(Mygenerator.underBlackAttack, 61) && !isContains(Mygenerator.underBlackAttack, 62) && !isContains(Mygenerator.underBlackAttack, 60)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean whiteLeftCastle() {
        if (whiteKingMoved == false && whiteRookLeft == false) {
            if (grid[7][1] == '.' && grid[7][2] == '.' && grid[7][3] == '.') {
                Mygenerator.setGrid(grid);
                Mygenerator.generateBlackAttackedCells();
                if (!isContains(Mygenerator.underBlackAttack, 58) && !isContains(Mygenerator.underBlackAttack, 59) && !isContains(Mygenerator.underBlackAttack, 60)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean blackRightCastle() {
        if (blackKingMoved == false && blackRookRight == false) {
            if (grid[0][5] == '.' && grid[0][6] == '.') {
                Mygenerator.setGrid(grid);
                Mygenerator.generateWhiteAttackedCells();
                if (!isContains(Mygenerator.underWhiteAttack, 4) && !isContains(Mygenerator.underWhiteAttack, 5) && !isContains(Mygenerator.underWhiteAttack, 6)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean blackLeftCastle() {
        if (blackKingMoved == false && blackRookLeft == false) {
            if (grid[0][1] == '.' && grid[0][2] == '.' && grid[0][3] == '.') {

                Mygenerator.setGrid(grid);
                Mygenerator.generateWhiteAttackedCells();
                if (!isContains(Mygenerator.underWhiteAttack, 2) && !isContains(Mygenerator.underWhiteAttack, 3) && !isContains(Mygenerator.underWhiteAttack, 4)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isContains(int a[], int key) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
                return true;
            }
        }
        return false;
    }

}
