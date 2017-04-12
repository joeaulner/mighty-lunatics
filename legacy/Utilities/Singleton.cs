using UnityEngine;

/// <summary>
/// The Singleton class implements a generic Singleton Pattern and inherits from
/// MonoBehaviour in order to allow subclasses to be attached to a GameObject.
/// 
/// <list type="Singleton<T>">
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
namespace ChessSwap.Utilities
{
    public class Singleton<T> : MonoBehaviour where T : MonoBehaviour
    {
        private static T instance;  // The singleton reference.

        /// <summary>
        /// Gets the singleton instance of this class, or instantiates one if there is none.
        /// </summary>
        /// <value>
        /// The shared instance of this class.
        /// </value>
        public static T Instance
        {
            get
            {
                // Returning a retained instance if there is one.
                if (instance != null)
                {   return  instance;   }


                // Checking for an already existing instance.
                instance = (T)FindObjectOfType(typeof(T));
                   
                // Returning a valid instance if there is one.
                if (instance != null)
                {   return instance;    }
                

                // Creating an empty Gamobject.
                GameObject go = new GameObject("*");

                // Setting the GameObject as temporary within the scene.
                go.hideFlags = HideFlags.HideAndDontSave;

                // Adding this class to the empty GameObject and referencing it.
                instance = go.AddComponent<T>();
                
                // Returning the instantiated instance.
                return instance;
            }
        }
    }
}