using UnityEngine;

namespace ChessSwap.Utilities
{
    /// <summary>
    /// LevelLoader is a helper class to make sure any static classes get cleaned up before
    /// loading the next scene.
    ///
    /// <list type="LevelLoader">
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
    public static class LevelLoader : System.Object
    {
        /// <summary>
        /// Clears Notifier's registered observers. Then loads the leve based on its index.
        /// </summary>
        public static void LoadLevel(int level)
        {
            Notifier.ClearRegisteredObservers();
            Application.LoadLevel(level);
        }

        /// <summary>
        /// Clears Notifier's registered observers. Then loads the leve based on its index.
        /// </summary>
        public static void LoadLevel(string level)
        {
            Notifier.ClearRegisteredObservers();
            Application.LoadLevel(level);
        }
    }
}