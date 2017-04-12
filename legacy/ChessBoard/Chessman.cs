using ChessSwap.Utilities;
using System.Collections;
using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>                                                                            
    /// Chessman is an abstract class used to control any object meant to move
    /// between Tile objects of the chessboard.
    /// 
    /// <list type="Chessman">
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
    public abstract class Chessman : OptMonoBehaviour
    {
        [SerializeField] protected Color color; // The color of the Chessman.
        private bool shouldMove = false;        // Should this script move the piece.
        private Vector2 targetPosition;         // Position to move towards if possible.

        /// <summary>
        /// This method sets the targetPosition and activates self movement.
        /// </summary>
        /// <param name="position">The target position (vector) to move towards.</param>
        public void MoveTo(Vector3 position)
        {
            this.targetPosition = new Vector2(position.x, position.y);
            this.shouldMove = true;
            StartCoroutine(MovePiece());
        }

        /// <summary>
        /// This method disables self movement of the Chessman.
        /// </summary>
        public void StopMoving()
        {
            this.shouldMove = false;
        }

        /// <summary>
        /// This method move this object until it is signaled to stop.
        /// </summary>
        private IEnumerator MovePiece()
        {
            while (this.shouldMove)
            {
                float xPosition = this.targetPosition.x - this.goTransform.position.x;
                float yPosition = this.targetPosition.y - this.goTransform.position.y;
                
                Vector3 direction = new Vector3(xPosition, yPosition, 0f);

                this.goTransform.Translate(0.15f*direction, Space.World);

                yield return new WaitForSeconds(0.001f);
            }
        }

        /// <summary>
        /// This method returns all possible movement with origin at this script's
        /// gameObject.
        /// </summary>
        /// <returns>
        /// An array of potential movement vectors (2D).
        /// </returns>
        public abstract Index2D[] MoveablePositions();


        /// <summary>
        /// This method returns the point value of matching three in a row.
        /// </summary>
        /// <returns>
        /// The point value of the Chessman object.
        /// </returns>
        public abstract int ScoreValue();
    }
}