package BattleOfBrains;

public class Board {

    public char array[][] = new char[8][8];

    public Board() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                array[i][j] = '.';
            }
        }

        array[0][1] = 'k';
        array[0][6] = 'k';
        array[7][6] = 'K';
        array[7][1] = 'K';

        array[0][2] = 'b';
        array[0][5] = 'b';
        array[7][2] = 'B';
        array[7][5] = 'B';

        array[0][0] = 'r';
        array[0][7] = 'r';
        array[7][0] = 'R';
        array[7][7] = 'R';

        array[0][3] = 'q';
        array[7][3] = 'Q';

        array[0][4] = 'x';
        array[7][4] = 'X';

        for (int i = 0; i <= 7; i++) {
            array[1][i] = 'p';
        }
        for (int i = 0; i <= 7; i++) {
            array[6][i] = 'P';
        }

    }
}
