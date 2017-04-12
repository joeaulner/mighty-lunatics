using ChessSwap.Managers;
using ChessSwap.Utilities;
using System.Collections;
using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// Tile is used used to represent a cell on a chessboard, with a container
    /// for a Chessman to be held and a reference to the BoardManager to swap
    /// pieces.
    /// 
    /// <list type="Tile">
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
    public sealed class Tile : OptMonoBehaviour, ISelectable
    {
        private Index2D index;      // 2D index of Tile within a board.
        private Chessman piece;     // Chessman component of child gameObject.

        /// <summary>
        /// Gets and sets the index property. If an Index2D is not set prior to getting, one
        /// is attempted to be made based on the name of the gameObject ("0 0"). If the set
        /// Index2D value is less than (0, 0) the index property will not be set to the
        /// passed value.
        /// </summary>
        /// <value>
        /// The Index2D of the tile in relation to the board.
        /// </value>
        public Index2D Index
        {
            get
            {
                if (this.index == default(Index2D))
                {   this.index = CalculateIndex();  }
                
                return this.index;
            }
            
            set
            {
                if (!(value.x < 0) && !(value.y < 0))
                {    this.index = value;    }
            }
        }

        /// <summary>
        /// Gets and sets the piece property. The new piece is moved to this Tile.
        /// </summary>
        /// <value>
        /// A Chessman attached to a GameObject.
        /// </value>
        public Chessman Piece
        {
            get
            {   return this.piece;  }
            
            set
            {
                this.piece = value;

                if (this.piece != null && this.piece.gameObject != null)
                {
                    this.piece.MoveTo(this.goTransform.position);
                    this.piece.goTransform.parent = this.goTransform;
                    StartCoroutine(ChessmanValidation());
                }
            }
        }

        /// <summary>
        /// This method notes whether it is selected or not and notifies all observers of
        /// selected Tile.
        /// </summary>
        /// <param name="tile">The Tile selected.</param>
        public void Select(Tile tile)
        {
            if (this == tile)
            {   Notifier.NotifyObservers(Notification.Tile_Is_Selected, this);  }
        }

        /// <summary>
        /// This method stops the Chessman object once it is in range of the Tile object. Then
        /// notifies all observers of the stopped Chessman object.
        /// </summary>
        private IEnumerator ChessmanValidation()
        {
            while (!InProximity())
            {
                yield return new WaitForSeconds(0.01f);
            }

            this.piece.StopMoving();
            Notifier.NotifyObservers(Notification.Tile_Stopped_Chessman, this);
        }

        /// <summary>
        /// This method clears the contents of the Tile object and notifies all observers of the change.
        /// </summary>
        public void Clear()
        {
            
            Notifier.NotifyObservers(Notification.Chessman_Deleted, this.Piece);

            if (this.Piece != null)
            {   Destroy(this.Piece.gameObject); }
            
            this.Piece = null;
            Notifier.NotifyObservers(Notification.Tile_Is_Empty, this);
        }

        /// <summary>
        /// This method creates an Index2D from the name of the gameObject ("0 0") => (0, 0).
        /// </summary>
        /// <returns>
        /// The Index2D calculated from the name of the gameObject ("0 0"). Or, (0, 0) if
        /// the coordinates are not withint the interval [1, 8].
        /// </returns>
        private Index2D CalculateIndex()
        {
            string[] coordinates = this.gameObject.name.Split(' ');

            int column = int.Parse(coordinates[0]);
            int row = int.Parse(coordinates[1]);

            if (column < 1 || column > 8 || row < 1 || row > 8)
            {   
                Debug.LogError("Chessman.CalculateIndex(): Invalid name => Index2D.");
                return Index2D.zero;
            }

            return new Index2D(column, row);
        }

        /// <summary>
        /// This method disables movement of the Chessman.
        /// </summary>
        /// <returns>
        /// If the space and piece are within range of each other, true; Otherwise, false.
        /// </returns>
        private bool InProximity()
        {
            if (this.piece == null)
            {   return false;   }

            float x = this.piece.goTransform.position.x - this.goTransform.position.x;
            float y = this.piece.goTransform.position.y - this.goTransform.position.y;

            float distance = x*x + y*y;

            return (distance < 0.00000025);
        }
    }
}