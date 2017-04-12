using ChessSwap.ChessBoard;
using ChessSwap.Utilities;
using UnityEngine;

namespace ChessSwap.Managers
{
    /// <summary>
    /// BoardManager is a singleton class which controls the interaction between the Tile objects.
    /// 
    /// <list type="BoardManager">
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
    public sealed class BoardManager : Singleton<BoardManager>
    {
        [SerializeField] private Board board;               // Array of Tile objects for the chessboard.
        [SerializeField] private ChessmanBuilder builder;   // Builder object for new Chessman.
        private Tile selectedTile;                          // Selected Tile retained for swapping.

        /// <summary>
        /// Unity Method: Start is called on the frame when a script is enabled
        /// just before any of the Update methods is called the first time.
        /// </summary>
        public void Start()
        {
            this.board = (Board)FindObjectOfType(typeof(Board));
            this.builder = this.gameObject.GetComponent<ChessmanBuilder>();

            Notifier.RegisterObserver(Notification.Tile_Is_Empty, FillEmptyTile);
            Notifier.RegisterObserver(Notification.Tile_Is_Selected, SelectTile);
            Notifier.RegisterObserver(Notification.Tile_Stopped_Chessman, CheckTiles);

            this.board.PopulateBoard();
        }

        /*TODO: Add a method to enable hinting mechanism.*/

        /// <summary>
        /// Creates a new Normal Chessman and places it as a parent to the given Tile object.
        /// </summary>
        /// <param name="tile">The Tile to parent the Chessman to.</param>
        public void CreatePieceForTile(Tile tile, Chessman previous = null)
        {
            Chessman piece = builder.CreateNormalPiece().GetComponent<Chessman>();

            if (previous != null)
            {
                while (piece.name.Equals(previous.name))
                {
                    Destroy(piece.gameObject);
                    piece = builder.CreateNormalPiece().GetComponent<Chessman>();
                }
            }

            float x = tile.goTransform.position.x;
            float y = tile.goTransform.position.y;

            Vector3 position = new Vector3(x, y, piece.goTransform.position.z);
            piece.goTransform.position = position;

            tile.Piece = piece;
        }
        
        /// <summary>
        /// This method checks for a match in the row and column of the passed Tile.
        /// This method is used to listen to the Tile_Stopped_Chessman Notification.
        /// </summary>
        /// <param name="sender">Must be a Tile MonoBehaviour.</param>
        private void CheckTiles(MonoBehaviour sender)
        {
            Tile tile = (Tile)sender;
            string name = tile.Piece.name;

            for (int i = 0; i < 2; i++)
            {
                int matchCombo = 0;
                Tile[] tiles = new Tile[8];

                for (int j = 1; j <= 8; j++)
                {
                    Tile tempTile;

                    if (i == 0)
                    {   tempTile = this.board.GetTile(tile.Index.x, j);    }
                    else
                    {   tempTile = this.board.GetTile(j, tile.Index.y);    }

                    if (tempTile != null)
                    {
                        if (tempTile.Piece != null)
                        {
                            if (name.Equals(tempTile.Piece.name))
                            {
                                tiles[matchCombo] = tempTile;
                                matchCombo += 1;
                            }
                            else if (matchCombo >= 3)
                            {   break;  }
                            else
                            {   matchCombo = 0; }
                        } else
                        {   matchCombo = 0; }
                    }
                }

                if (matchCombo > 2)
                {
                    for (int j = 0; j < matchCombo; j++)
                    {   tiles[j].Clear();   }
                }
            }
        }

        /// <summary>
        /// This method fills all empty Tiles in the given tiles column strating with this tile.
        /// This method is used to listen to the Tile_Is_Empty Notification.
        /// </summary>
        /// <param name="sender">Must be a Tile MonoBehaviour.</param>
        private void FillEmptyTile(MonoBehaviour sender)
        {
            Tile tile = (Tile)sender;
            RefillColumn(tile.Index.x, tile.Index.y);
        }

        /// <summary>
        /// This method checks if the Chessman at the given Tile can move to the given Index2D.
        /// </summary>
        /// <returns>
        /// Whether the Chessman at the given tile can move to the new Index2D.
        /// </returns>
        private bool IsValidMove(Tile tile, Index2D newIndex)
        {
            Index2D[] indices = tile.Piece.MoveablePositions();

            foreach (Index2D index in indices)
            {
                if (newIndex == tile.Index + index)
                {   return true;    }
            }

            return false;
        }

        /// <summary>
        /// This method retains, swaps, and deselects the Tile passed depending on if a Tile
        /// object is retained or not and if the passed Tile is the retained Tile or not.
        /// </summary>
        /// <param name="column"></param>
        /// <param name="startY">The starting row position to begin refilling all Tile objects.</param>
        private void RefillColumn(int column, int startY = 1)
        {
            Tile lowerTile = this.board.GetTile(column, startY);
            for (int j = startY; j <= 7 ; j++)
            {
                Tile higherTile = this.board.GetTile(column, j+1);
                this.board.SwapTiles(lowerTile, higherTile);
                lowerTile = higherTile;
            }

            CreatePieceForTile(this.board.GetTile(column, 8));
        }

        /// <summary>
        /// This method retains, swaps, and deselects the Tile passed depending on if a Tile
        /// object is retained or not and if the passed Tile is the retained Tile or not.
        /// This method is used to listen to the Tile_Is_Selected Notification.
        /// </summary>
        /// <param name="sender">Must be a Tile MonoBehaviour.</param>
        private void SelectTile(MonoBehaviour sender)
        {
            Tile tile = (Tile)sender;

            if (this.selectedTile == null)
            {   this.selectedTile = tile;   }
            else if (this.selectedTile != tile && IsValidMove(this.selectedTile, tile.Index))
            {
                this.board.SwapTiles(this.selectedTile, tile);
                Notifier.NotifyObservers(Notification.Player_Did_Move, this);
                this.selectedTile = null;
            }
            else
            {   this.selectedTile = null;   }
        }
    }
}