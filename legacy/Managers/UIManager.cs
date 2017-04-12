using ChessSwap.ChessBoard;
using ChessSwap.Utilities;
using ChessSwap.UI;
using UnityEngine;

namespace ChessSwap.Managers
{
    /// <summary>
    /// 
    /// 
    /// <list type="UIManager">
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
    public sealed class UIManager : Singleton<UIManager>
    {
        public ScoreView scoreView;     // Reference to the ScoreView object.

        /// <summary>
        /// Unity Method: Start is called on the frame when a script is enabled
        /// just before any of the Update methods is called the first time.
        /// </summary>
        public void Start()
        {
            Notifier.RegisterObserver(Notification.Score_Did_Change, UpdateScoreView);

            if (scoreView != null)
            {   scoreView.UpdateScore();    }
        }

        /// <summary>
        /// This method directs the ScoreView property to update its text.
        /// This method is used to listen to the Score_Did_Change Notification.
        /// </summary>
        /// <param name="sender">Any valid class.</param>
        private void UpdateScoreView(MonoBehaviour sender)
        {
            if (scoreView != null)
            {   scoreView.UpdateScore();    }
        }
    }
}