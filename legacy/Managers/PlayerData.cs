using ChessSwap.ChessBoard;
using ChessSwap.Utilities;
using UnityEngine;

namespace ChessSwap.Managers
{
    /// <summary>
    /// TODO
    /// 
    /// <list type="PlayerData">
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
    public sealed class PlayerData : Singleton<PlayerData>
    {
        private int score = 0;              // The player's current score.
        private int remainingMoves = 30;    // The player's remaining moves.

        /// <summary>
        /// Gets and sets the score property. The score change is notified to all observers.
        /// </summary>
        /// <value>
        /// An int value.
        /// </value>
        public int Score
        {
            get
            {   return this.score;  }

            set
            {
                this.score = value;
                Notifier.NotifyObservers(Notification.Score_Did_Change, this);
            }
        }

        /// <summary>
        /// Gets and sets the remainingMoves property. The move change is notified to all observers.
        /// </summary>
        /// <value>
        /// An int value.
        /// </value>
        public int Moves
        {
            get
            {   return this.remainingMoves;  }

            set
            {   
                this.remainingMoves = value;
                Notifier.NotifyObservers(Notification.Score_Did_Change, this);

                if (this.remainingMoves <= 0)
                {   Notifier.NotifyObservers(Notification.Level_Ended, this);   }
            }
        }
    }
}