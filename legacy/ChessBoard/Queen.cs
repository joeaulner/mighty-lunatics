using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// Queen is a class used to refer to any Queen object meant to move between tiles of
    /// the chessboard.
    /// 
    /// <list type="Queen">
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
    public sealed class Queen : Chessman
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
                2*Index2D.up,
                3*Index2D.up,
                4*Index2D.up,
                5*Index2D.up,
                6*Index2D.up,
                7*Index2D.up,
                8*Index2D.up,
                9*Index2D.up,
                10*Index2D.up,

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

                1*Index2D.down,
                2*Index2D.down,
                3*Index2D.down,
                4*Index2D.down,
                5*Index2D.down,
                6*Index2D.down,
                7*Index2D.down,
                8*Index2D.down,
                9*Index2D.down,
                10*Index2D.down,

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
        {   return 55;  }
    }
}