package javagames.game.chessboard;

import javagames.game.structs.Index2D;

public final class Pawn extends Chessman {

    private int direction;

    public Pawn(int direction) {
        this.direction = direction;
    }

    /**
     * @see Chessman#moveablePositions()
     * @return An array of potential movement Positions (2D).
     */
    @Override
    public Index2D[] moveablePositions() {
        return new Index2D[] {
            Index2D.mul(direction, Index2D.leftUp),
            Index2D.mul(direction, Index2D.rightUp)
        };
    }

    /**
     * @see Chessman#scoreValue()
     * @return The point value of this Chessman object.
     */
    @Override
    public int scoreValue() {
        return 3;
    }
}
