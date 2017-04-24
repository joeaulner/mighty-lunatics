package javagames.game.chessboard;

import javagames.game.structs.Index2D;

public final class Bishop extends Chessman {

    public Bishop() {
        super("bishop");
    }

    /**
     * @see Chessman#moveablePositions()
     * @return An array of potential movement Positions (2D).
     */
    @Override
    public Index2D[] moveablePositions() {
        return new Index2D[] {
            Index2D.mul(1, Index2D.rightUp),
            Index2D.mul(2, Index2D.rightUp),
            Index2D.mul(3, Index2D.rightUp),
            Index2D.mul(4, Index2D.rightUp),
            Index2D.mul(5, Index2D.rightUp),
            Index2D.mul(6, Index2D.rightUp),
            Index2D.mul(7, Index2D.rightUp),
            Index2D.mul(8, Index2D.rightUp),
            Index2D.mul(9, Index2D.rightUp),
            Index2D.mul(10, Index2D.rightUp),

            Index2D.mul(1, Index2D.rightDown),
            Index2D.mul(2, Index2D.rightDown),
            Index2D.mul(3, Index2D.rightDown),
            Index2D.mul(4, Index2D.rightDown),
            Index2D.mul(5, Index2D.rightDown),
            Index2D.mul(6, Index2D.rightDown),
            Index2D.mul(7, Index2D.rightDown),
            Index2D.mul(8, Index2D.rightDown),
            Index2D.mul(9, Index2D.rightDown),
            Index2D.mul(10, Index2D.rightDown),

            Index2D.mul(1, Index2D.leftUp),
            Index2D.mul(2, Index2D.leftUp),
            Index2D.mul(3, Index2D.leftUp),
            Index2D.mul(4, Index2D.leftUp),
            Index2D.mul(5, Index2D.leftUp),
            Index2D.mul(6, Index2D.leftUp),
            Index2D.mul(7, Index2D.leftUp),
            Index2D.mul(8, Index2D.leftUp),
            Index2D.mul(9, Index2D.leftUp),
            Index2D.mul(10, Index2D.leftUp),

            Index2D.mul(1, Index2D.leftDown),
            Index2D.mul(2, Index2D.leftDown),
            Index2D.mul(3, Index2D.leftDown),
            Index2D.mul(4, Index2D.leftDown),
            Index2D.mul(5, Index2D.leftDown),
            Index2D.mul(6, Index2D.leftDown),
            Index2D.mul(7, Index2D.leftDown),
            Index2D.mul(8, Index2D.leftDown),
            Index2D.mul(9, Index2D.leftDown),
            Index2D.mul(10, Index2D.leftDown)
        };
    }

    /**
     * @see Chessman#scoreValue()
     * @return The point value of this Chessman object.
     */
    @Override
    public int scoreValue() {
        return 10;
    }
}
