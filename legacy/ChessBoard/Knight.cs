using UnityEngine;

namespace ChessSwap.ChessBoard
{
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
    public sealed class Knight : Chessman
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
                2*Index2D.up + Index2D.right,
                2*Index2D.up + Index2D.left,

                Index2D.up + 2*Index2D.right,
                Index2D.down + 2*Index2D.right,

                2*Index2D.down + Index2D.right,
                2*Index2D.down + Index2D.left,
                
                Index2D.up + 2*Index2D.left,
                Index2D.down + 2*Index2D.left
            };
        }

        /// <summary>
        /// This method returns the point value of matching three in a row.
        /// </summary>
        /// <returns>
        /// The point value of the Chessman object.
        /// </returns>
        public override int ScoreValue()
        {   return 40;  }
    }
}