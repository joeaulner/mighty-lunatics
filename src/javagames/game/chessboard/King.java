package javagames.game.chessboard;
import javagames.game.structs.Index2D;

public final class King extends Chessman {

    /**
     * @see Chessman#moveablePositions()
     * @return An array of potential movement Positions (2D).
     */
	@Override
    public Index2D[] moveablePositions() {
        return new Index2D[] {
            Index2D.up,
            Index2D.rightUp,
            Index2D.right,
            Index2D.rightDown,
            Index2D.down,
            Index2D.leftDown,
            Index2D.left,
            Index2D.leftUp
        };
    }

    /**
     * @see Chessman#scoreValue()
     * @return The point value of this Chessman object.
     */
	@Override
    public int scoreValue() {
	    return 100;
	}
}