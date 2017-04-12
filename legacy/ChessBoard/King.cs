using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// King is a class used to refer to any King object meant to move between tiles of
    /// the chessboard.
    /// 
    /// <list type="King">
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
    public sealed class King : Chessman
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

        /// <summary>
        /// This method returns the point value of matching three in a row.
        /// </summary>
        /// <returns>
        /// The point value of the Chessman object.
        /// </returns>
        public override int ScoreValue()
        {   return 50;  }
    }
}