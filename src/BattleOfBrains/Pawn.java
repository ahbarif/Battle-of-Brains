package BattleOfBrains;


public class Pawn extends Piece {

    //   -------------------------------    constructor
    public boolean isMoved;

    public Pawn(String s, int x, int y, char[][] array, boolean f) {
        super(s, x, y, array);
        isMoved = f;
        generateLegalMoves();
    }

    //   -------------------------------    generating legal moves
    public void generateLegalMoves() {


        if (PieceColor.equalsIgnoreCase("white")) {

            if (insideBoard(x - 1, y) && array[x - 1][y] == '.') {
                LegalMoves[count++] = (x - 1) * 8 + y;
            }
            if (insideBoard(x - 1, y) && isMoved == false && array[x - 1][y] == '.') {
                if (insideBoard(x - 2, y) && array[x - 2][y] == '.') {
                    LegalMoves[count++] = (x - 2) * 8 + y;
                }
            }
            if (insideBoard(x - 1, y - 1) && array[x - 1][y - 1] != '.' && !isSameColor(x, y, x - 1, y - 1)) {
                LegalMoves[count++] = (x - 1) * 8 + (y - 1);
            }
            if (insideBoard(x - 1, y + 1) && array[x - 1][y + 1] != '.' && !isSameColor(x, y, x - 1, y + 1)) {
                LegalMoves[count++] = (x - 1) * 8 + (y + 1);
            }
        } else {
            if (insideBoard(x + 1, y) && array[x + 1][y] == '.') {
                LegalMoves[count++] = (x + 1) * 8 + y;
            }
            if (insideBoard(x + 1, y) && isMoved == false && array[x + 1][y] == '.') {
                if (insideBoard(x + 2, y) && array[x + 2][y] == '.') {
                    LegalMoves[count++] = (x + 2) * 8 + y;
                }
            }
            if (insideBoard(x + 1, y - 1) && array[x + 1][y - 1] != '.' && !isSameColor(x, y, x + 1, y - 1)) {
                LegalMoves[count++] = (x + 1) * 8 + (y - 1);
            }
            if (insideBoard(x + 1, y + 1) && array[x + 1][y + 1] != '.' && !isSameColor(x, y, x + 1, y + 1)) {
                LegalMoves[count++] = (x + 1) * 8 + (y + 1);
            }

        }

    }

    public boolean insideBoard(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    

}
