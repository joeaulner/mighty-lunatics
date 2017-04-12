using ChessSwap.Managers;
using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// The Board class is a model component for manipulating the Chessmen and Tiles of the Board.
    /// 
    /// <list type="Board">
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
    public sealed class Board : MonoBehaviour
    {
        [SerializeField] private bool showTiles = true;
        private Tile[] board;   // Array of Tiles for the chessboard.

        /// <summary>
        /// Unity Method: This function is called when the script is loaded or a value is changed in
        /// the inspector (Called in the editor only).
        /// </summary>
        public void OnValidate()
        {
            MeshRenderer[] renderers = this.GetComponentsInChildren<MeshRenderer>();

            foreach (MeshRenderer renderer in renderers)
            {
                renderer.enabled = showTiles;
            }
        }

        /// <summary>
        /// Unity Method: Awake is used to initialize any variables or game state before the game starts.
        /// Awake is called only once during the lifetime of the script instance. Awake is called after
        /// all objects are initialized so you can safely speak to other objects or query them using
        /// eg. GameObject.FindWithTag. Each GameObject's Awake is called in a random order between
        /// objects. Because of this, you should use Awake to set up references between scripts, and
        /// use Start to pass any information back and forth. Awake is always called before any Start
        /// functions. This allows you to order initialization of scripts. Awake can not act as a coroutine.
        /// </summary>
        public void Awake()
        {
            this.board = (Tile[])GetComponentsInChildren<Tile>();
        }

        /// <summary>
        /// This method swaps the Piece properties of the 2 tiles passed.
        /// </summary>
        /// <param name="initialTile">The Tile to move from.</param>
        /// <param name="finalTile">The Tile to move to.</param>
        public void SwapTiles(Tile initialTile, Tile finalTile)
        {
            Chessman piece = finalTile.Piece;
            finalTile.Piece = initialTile.Piece;
            initialTile.Piece = piece;
        }

        /// <summary>
        /// This method populates the board at the beginning of the level.
        /// </summary>
        public void PopulateBoard()
        {
            Chessman previous = null;
            foreach (Tile tile in this.board)
            {   
                BoardManager.Instance.CreatePieceForTile(tile, previous);
                previous = tile.Piece;
            }
        }

        /// <summary>
        /// This method returns a Tile object with the given index if there is one.
        /// Otherwise, null.
        /// </summary>
        /// <param name="x">The x-coordinate of the Index2D to search for.</param>
        /// <param name="y">The y-coordinate of the Index2D to search for.</param>
        /// <returns>
        /// The Tile with the given index. Otherwise, null.
        /// </returns>
        public Tile GetTile(int x, int y)
        {
            return this.GetTile(new Index2D(x, y));
        }

        /// <summary>
        /// This method returns a Tile object with the given index if there is one.
        /// Otherwise, null.
        /// </summary>
        /// <param name="index">The Index2D to search for.</param>
        /// <returns>
        /// The Tile with the given index. Otherwise, null.
        /// </returns>
        public Tile GetTile(Index2D index)
        {
            foreach(Tile tile in this.board)
            {
                if (index == tile.Index)
                {    return tile;   }
            }

            return null;
        }
    }
}