using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// Pawn is a class used to refer to any Pawn object meant to move between tiles of the
    /// chessboard.
    /// 
    /// <list type="Pawn">
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
    public sealed class Pawn : Chessman
    {
        /// <summary>
        /// This method returns all possible movement with origin at this script's
        /// gameObject.
        /// </summary>
        /// <returns>
        /// An array of potential movement directions (2D).
        /// </returns>
        public  override Index2D[] MoveablePositions()
        {
            return new Index2D[]
            {
                ((int)this.color)*Index2D.leftUp,
                ((int)this.color)*Index2D.rightUp
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