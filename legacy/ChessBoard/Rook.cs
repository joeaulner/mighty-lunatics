using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>                                                                            
    /// Rook is a class used to refer to any Rook object meant to move between tiles of
    /// the chessboard.
    /// 
    /// <list type="Rook">
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
    public sealed class Rook : Chessman
    {
        /// <summary>
        /// This method returns all possible movement with origin at this script's
        /// gameObject.
        /// </summary>
        /// <returns>
        /// An array of potential movement Positions (2D).
        /// </returns>
        public override Index2D[] MoveablePositions()
        {
            return new Index2D[]
            {
                Index2D.right,
                2*Index2D.right,
                3*Index2D.right,
                4*Index2D.right,
                5*Index2D.right,
                6*Index2D.right,
                7*Index2D.right,
                8*Index2D.right,
                9*Index2D.right,
                10*Index2D.right,

                Index2D.left,
                2*Index2D.left,
                3*Index2D.left,
                4*Index2D.left,
                5*Index2D.left,
                6*Index2D.left,
                7*Index2D.left,
                8*Index2D.left,
                9*Index2D.left,
                10*Index2D.left,

                Index2D.up,
                2*Index2D.up,
                3*Index2D.up,
                4*Index2D.up,
                5*Index2D.up,
                6*Index2D.up,
                7*Index2D.up,
                8*Index2D.up,
                9*Index2D.up,
                10*Index2D.up,

                1*Index2D.down,
                2*Index2D.down,
                3*Index2D.down,
                4*Index2D.down,
                5*Index2D.down,
                6*Index2D.down,
                7*Index2D.down,
                8*Index2D.down,
                9*Index2D.down,
                10*Index2D.down
            };
        }

        /// <summary>
        /// This method returns the point value of matching three in a row.
        /// </summary>
        /// <returns>
        /// The point value of the Chessman object.
        /// </returns>
        public override int ScoreValue()
        {   return 30;  }
    }
}