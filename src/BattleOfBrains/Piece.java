package BattleOfBrains;

public class Piece {

    public String PieceColor;
    public int x, y, count;
    public char[][] array;

    public int LegalMoves[] = new int[50];

    public Piece(String PieceColor, int x, int y, char[][] array) {
        this.PieceColor = PieceColor;
        this.x = x;
        this.y = y;
        this.array = array;
    }

      public int[] getLegalMoves() {
     
        int tmp[] = new int[count];

        System.arraycopy(LegalMoves, 0, tmp, 0, count);

        return tmp;
    }

    public boolean isSameColor(int i, int j, int x, int y) {
        if (array[i][j] >= 'a' && array[i][j] <= 'z' && array[x][y] >= 'a' && array[x][y] <= 'z') {
            return true;
        } else {
            return array[i][j] >= 'A' && array[i][j] <= 'Z' && array[x][y] >= 'A' && array[x][y] <= 'Z';
        }
    }

}
