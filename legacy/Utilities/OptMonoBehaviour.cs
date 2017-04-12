using UnityEngine;

namespace ChessSwap.Utilities
{
    /// <summary>
    /// An Optimizing MonoBehaviour used to deal with expensive tasks like GetComponent<T>()
    /// (To be imporoved as necessary).
    /// 
    /// <list type="OptMonoBehaviour">
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
    public class OptMonoBehaviour : MonoBehaviour
    {
        public Transform goTransform;   // GameObject transform reference.

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
            this.goTransform = this.transform;
        }
    }
}