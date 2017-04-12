using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// Bishop is a class used to refer to any Bishop object meant to move between tiles of
    /// the chessboard.
    /// 
    /// <list type="Bishop">
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
    public sealed class Bishop : Chessman
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
                Index2D.rightUp,
                2*Index2D.rightUp,
                3*Index2D.rightUp,
                4*Index2D.rightUp,
                5*Index2D.rightUp,
                6*Index2D.rightUp,
                7*Index2D.rightUp,
                8*Index2D.rightUp,
                9*Index2D.rightUp,
                10*Index2D.rightUp,

                Index2D.rightDown,
                2*Index2D.rightDown,
                3*Index2D.rightDown,
                4*Index2D.rightDown,
                5*Index2D.rightDown,
                6*Index2D.rightDown,
                7*Index2D.rightDown,
                8*Index2D.rightDown,
                9*Index2D.rightDown,
                10*Index2D.rightDown,

                Index2D.leftDown,
                2*Index2D.leftDown,
                3*Index2D.leftDown,
                4*Index2D.leftDown,
                5*Index2D.leftDown,
                6*Index2D.leftDown,
                7*Index2D.leftDown,
                8*Index2D.leftDown,
                9*Index2D.leftDown,
                10*Index2D.leftDown,

                Index2D.leftUp,
                2*Index2D.leftUp,
                3*Index2D.leftUp,
                4*Index2D.leftUp,
                5*Index2D.leftUp,
                6*Index2D.leftUp,
                7*Index2D.leftUp,
                8*Index2D.leftUp,
                9*Index2D.leftUp,
                10*Index2D.leftUp
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