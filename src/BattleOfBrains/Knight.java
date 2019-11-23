package BattleOfBrains;

public class Knight extends Piece {

    public Knight(String s, int x, int y, char[][] array) {
        super(s, x, y, array);
        count = 0;
        generateLegalMoves();
    }

    public void generateLegalMoves() {

        int dx[] = {+1, -1, -1, +1, +2, +2, -2, -2};
        int dy[] = {-2, -2, +2, +2, -1, +1, +1, -1};

        int i, j;

        for (i = 0; i < 8; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (tx < 0 || tx >= 8 || ty < 0 || ty >= 8) {
                continue;
            }

            if (array[tx][ty] == '.') {
                LegalMoves[count++] = tx * 8 + ty;
            } else {
                if (!isSameColor(x, y, tx, ty)) {
                    LegalMoves[count++] = tx * 8 + ty;
                }

            }
        }

    }

}
