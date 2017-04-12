using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// ChessmanBuilder is a class which builds random and specific instances of the Chessman
    /// class to be used in the Board.
    /// 
    /// <list type="ChessmanBuilder">
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
    public sealed class ChessmanBuilder : MonoBehaviour
    {
        [SerializeField] private GameObject[] pieces;   // An array of normal Chessmen.
        [SerializeField] private GameObject queen;      // The queen piece.
        [SerializeField] private GameObject king;       // The king piece.

        /// <summary>
        /// Creates a randomly picked Piece that is not a King or Queen.
        /// </summary>
        /// <returns>
        /// A GameObject from one of the given Prefabs.
        /// </returns>
        public GameObject CreateNormalPiece()
        {
            if (this.pieces.Length <= 0)
            {   return new GameObject("No Pieces"); }

            return GameObject.Instantiate(this.pieces[Random.Range(0, this.pieces.Length-1)]);;
        }

        /// <summary>
        /// Creates a Queen Chessman GameObject.
        /// </summary>
        /// <returns>
        /// A GameObject from one of the given Prefabs.
        /// </returns>
        public GameObject CreateQueenPiece()
        {
            if (this.king == null)
            {   return new GameObject("No Queen"); }

            return GameObject.Instantiate(this.king);
        }

        /// <summary>
        /// Creates a King Chessman GameObject.
        /// </summary>
        /// <returns>
        /// A GameObject from one of the given Prefabs.
        /// </returns>
        public GameObject CreateKingPiece()
        {
            if (this.king == null)
            {   return new GameObject("No King"); }

            return GameObject.Instantiate(this.king);
        }
    }
}