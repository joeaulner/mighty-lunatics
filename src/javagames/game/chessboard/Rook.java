package javagames.game.chessboard;

import javagames.game.structs.Index2D;

public final class Rook extends Chessman {

    public Rook() {
        super("rook");
    }

    /**
     * @see Chessman#moveablePositions()
     * @return An array of potential movement Positions (2D).
     */
    @Override
    public Index2D[] moveablePositions() {
        return new Index2D[] {
            Index2D.mul(1, Index2D.right),
            Index2D.mul(2, Index2D.right),
            Index2D.mul(3, Index2D.right),
            Index2D.mul(4, Index2D.right),
            Index2D.mul(5, Index2D.right),
            Index2D.mul(6, Index2D.right),
            Index2D.mul(7, Index2D.right),
            Index2D.mul(8, Index2D.right),
            Index2D.mul(9, Index2D.right),
            Index2D.mul(10, Index2D.right),

            Index2D.mul(1, Index2D.left),
            Index2D.mul(2, Index2D.left),
            Index2D.mul(3, Index2D.left),
            Index2D.mul(4, Index2D.left),
            Index2D.mul(5, Index2D.left),
            Index2D.mul(6, Index2D.left),
            Index2D.mul(7, Index2D.left),
            Index2D.mul(8, Index2D.left),
            Index2D.mul(9, Index2D.left),
            Index2D.mul(10, Index2D.left),

            Index2D.mul(1, Index2D.up),
            Index2D.mul(2, Index2D.up),
            Index2D.mul(3, Index2D.up),
            Index2D.mul(4, Index2D.up),
            Index2D.mul(5, Index2D.up),
            Index2D.mul(6, Index2D.up),
            Index2D.mul(7, Index2D.up),
            Index2D.mul(8, Index2D.up),
            Index2D.mul(9, Index2D.up),
            Index2D.mul(10, Index2D.up),

            Index2D.mul(1, Index2D.down),
            Index2D.mul(2, Index2D.down),
            Index2D.mul(3, Index2D.down),
            Index2D.mul(4, Index2D.down),
            Index2D.mul(5, Index2D.down),
            Index2D.mul(6, Index2D.down),
            Index2D.mul(7, Index2D.down),
            Index2D.mul(8, Index2D.down),
            Index2D.mul(9, Index2D.down),
            Index2D.mul(10, Index2D.down),
        };
    }

    /**
     * @see Chessman#scoreValue()
     * @return The point value of this Chessman object.
     */
    @Override
    public int scoreValue() {
        return 25;
    }
}
