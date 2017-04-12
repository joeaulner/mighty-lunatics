using UnityEngine;

namespace ChessSwap.ChessBoard
{
    /// <summary>
    /// TileTouch picks up a touch for a given Tile and relays said touch to the Tile.
    /// 
    /// <list type="TileTouch">
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
    public sealed class TileTouch : MonoBehaviour
    {
        private Tile tile;  // The tile to send this message to.

        /// <summary>
        /// Unity Method: Start is called on the frame when a script is enabled
        /// just before any of the Update methods is called the first time.
        /// </summary>
        public void Start()
        {
            if (this.tile == null)
            {   this.tile = this.transform.parent.GetComponent<Tile>(); }
        }

        /// <summary>
        /// Unity Method: Update is called every frame, if the MonoBehaviour is enabled.
        /// </summary>
        public void OnMouseDown()
        {
            #if GAME_DEBUG
            Debug.Log("TileTouch.OnMouseDown(): Method Called.", this);
            #endif

            this.tile.Select(this.tile);
        }
    }
}