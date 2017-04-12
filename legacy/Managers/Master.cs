using ChessSwap.ChessBoard;
using ChessSwap.Utilities;
using UnityEngine;

namespace ChessSwap.Managers
{
    /// <summary>
    /// BoardManager is a singleton class which controls the interaction between the Tile objects.
    /// 
    /// <list type="Master">
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
    public sealed class Master : MonoBehaviour
    {
        PlayerData pdata;   // Reference to the PlayerData class.

        /// <summary>
        /// Unity Method: Start is called on the frame when a script is enabled
        /// just before any of the Update methods is called the first time.
        /// </summary>
        public void Start()
        {
            this.pdata = PlayerData.Instance;
            Notifier.RegisterObserver(Notification.Player_Did_Move, DecrementMoveCount);
            Notifier.RegisterObserver(Notification.Chessman_Deleted, UpdateScore);
            Notifier.RegisterObserver(Notification.Level_Ended, EndLevel);
        }

        /// <summary>
        /// This method updates the Players moves remaining.
        /// This method is used to listen to the Player_Did_Move Notification.
        /// </summary>
        /// <param name="sender">Any valid class.</param>
        private void DecrementMoveCount(MonoBehaviour sender)
        {
            this.pdata.Moves--;

            #if GAME_DEBUG
            Debug.Log("Master.DecrementMoveCount: " + this.pdata.Moves);
            #endif
        }

        /// <summary>
        /// This method updates the Player's score.
        /// This method is used to listen to the Chessman_Deleted Notification.
        /// </summary>
        /// <param name="sender">Must be a Chessman MonoBehaviour.</param>
        private void UpdateScore(MonoBehaviour sender)
        {
            this.pdata.Score += ((Chessman) sender).ScoreValue();
            
            #if GAME_DEBUG
            Debug.Log("Master.UpdateScore: " + this.pdata.Score);
            #endif
        }

        /// <summary>
        /// This method reloads the scene at the end of the level.
        /// This method is used to listen to the Level_Ended Notification.
        /// </summary>
        /// <param name="sender">Any valid class.</param>
        private void EndLevel(MonoBehaviour sender)
        {
            LevelLoader.LoadLevel(0);
        }
    }
}