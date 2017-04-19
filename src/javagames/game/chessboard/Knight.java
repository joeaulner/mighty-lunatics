package javagames.game.chessboard;
import javagames.game.structs.Index2D;
/// <summary>
/// Knight is a class used to refer to any Knight object meant to move between tiles of
/// the chessboard.
/// 
/// <list type="Knight">
/// <item>
/// <term>Author</term>
/// <description>Nathan Skalka (nathansmail@cox.net)</description>
/// </item>
/// <item>
/// <term>Copyright</term>
/// <description>Nathan Skalka (c) 2015</description>
/// </item>
/// </list>
/// 
/// </summary>
public final class Knight extends Chessman {
	
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
    
    @Override
    public int scoreValue() {   return 40;  }
}
