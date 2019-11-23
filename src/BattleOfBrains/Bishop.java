package BattleOfBrains;

public class Bishop extends Piece {

    public Bishop(String s, int x, int y, char[][] array) {
        super(s, x, y, array);
        count = 0;
        generateLegalMoves();
    }

    public void generateLegalMoves() {

        int dx[] = {+1, +1, -1, -1};
        int dy[] = {-1, +1, +1, -1};

        int p, q, i, j;

        for (int k = 0; k < 4; k++) {
            p = dx[k];
            q = dy[k];
            i = x;
            j = y;

            while (true) {
                i += p;
                j += q;

                if (i < 0 || i > 7 || j < 0 || j > 7) {
                    break;
                }

                if (array[i][j] == '.') {
                    LegalMoves[count++] = i * 8 + j;
                } else {

                    if (isSameColor(x, y, i, j)) {
                        break;
                    } else {
                        LegalMoves[count++] = i * 8 + j;
                        break;
                    }

                }

            }
        }

    }

}
