package javagames.game.chessboard;
import javagames.game.structs.Index2D;

public final class Knight extends Chessman {

    /**
     * @see Chessman#moveablePositions()
     * @return An array of potential movement Positions (2D).
     */
	@Override
    public Index2D[] moveablePositions() {
        return new Index2D[] {
            Index2D.add(Index2D.mul(2, Index2D.up), Index2D.right),
            Index2D.add(Index2D.mul(2, Index2D.up), Index2D.left),

            Index2D.add(Index2D.up, Index2D.mul(2, Index2D.right)),
            Index2D.add(Index2D.down, Index2D.mul(2, Index2D.right)),

            Index2D.add(Index2D.mul(2, Index2D.down), Index2D.right),
            Index2D.add(Index2D.mul(2, Index2D.down), Index2D.left),

            Index2D.add(Index2D.up, Index2D.mul(2, Index2D.left)),
            Index2D.add(Index2D.down, Index2D.mul(2, Index2D.left))
        };
    }

    /**
     * @see Chessman#scoreValue()
     * @return The point value of this Chessman object.
     */
    @Override
    public int scoreValue() {
        return 5;
    }
}
