package BattleOfBrains;

public class Rook extends Piece {

    int turn;

    public Rook(String s, int x, int y, char[][] array) {
        super(s, x, y, array);
        count = 0;
        generateLegalMoves();
    }

    public void generateLegalMoves() {

        count = 0;

        for (int i = x + 1; i <= 7; i++) {
            if (array[i][y] == '.') {
                LegalMoves[count++] = i * 8 + y;
            } else {
                if (!isSameColor(x, y, i, y)) {
                    LegalMoves[count++] = i * 8 + y;
                }
                break;
            }
        }

        for (int i = x - 1; i >= 0; i--) {
            if (array[i][y] == '.') {
                LegalMoves[count++] = i * 8 + y;
            } else {
                if (!isSameColor(x, y, i, y)) {
                    LegalMoves[count++] = i * 8 + y;
                }
                break;
            }
        }

        // checking right
        for (int i = y + 1; i <= 7; i++) {
            if (array[x][i] == '.') {
                LegalMoves[count++] = x * 8 + i;
            } else {
                if (!isSameColor(x, i, x, y)) {
                    LegalMoves[count++] = x * 8 + i;
                }
                break;
            }
        }

        //   chekcing left
        for (int i = y - 1; i >= 0; i--) {
            if (array[x][i] == '.') {
                LegalMoves[count++] = x * 8 + i;
            } else {
                if (!isSameColor(x, i, x, y)) {
                    LegalMoves[count++] = x * 8 + i;
                }
                break;
            }
        }

    }

   
}
