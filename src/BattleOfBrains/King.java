
package BattleOfBrains;

public class King extends Piece {

    
    public King(String s, int x, int y, char[][] array) {
        super(s, x, y, array);
        count = 0;
        generateLegalMoves();
    }

    public void generateLegalMoves() {
        count = 0;

        int dx[] = {-1, -1, -1, +1, +1, +1, 0, 0};
        int dy[] = {0, -1, +1, 0, -1, +1, +1, -1};

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
