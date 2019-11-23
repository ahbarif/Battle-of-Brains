package BattleOfBrains;

public class Move {

    public int fromX, fromY, toX, toY;

    public Move(int fromX, int fromY, int toX, int toY) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public String toString()
    {
        return (fromX + " " + fromY+ " " + toX+ " " + toY);
        
    }
}
