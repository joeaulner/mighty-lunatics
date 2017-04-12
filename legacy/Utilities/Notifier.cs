using UnityEngine;
using System.Collections.Generic;

namespace ChessSwap.Utilities
{
    /// <summary>
    /// Notifier is a horrible example of an Observer pattern utilized in this game,
    /// and is, in fact, more of a mediator class than a subscribe-publish pattern.
    /// 
    /// <list type="Notifier">
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
    public static class Notifier : System.Object
    {
        public delegate void NotificationMethod(MonoBehaviour sender);  // Notification Delegate

        // Observer References
        private static Dictionary<Notification, List<NotificationMethod>> dictionary
                = new Dictionary<Notification, List<NotificationMethod>>();

        /// <summary>
        /// Registers a specific Method to a given Notification.
        /// </summary>
        /// <param name="notification">The Notification to be broadcast.</param>
        /// <param name="method">The method for the notification to call.</param>
        public static void RegisterObserver(Notification notification, NotificationMethod method)
        {
            if (dictionary.ContainsKey(notification))
            {
                #if GAME_DEBUG
                Debug.Log("Adding observing method (" + method + ") to Notification: " + notification);
                #endif

                if (!dictionary[notification].Contains(method))
                {   dictionary[notification].Add(method);  }
            }
            else
            {
                #if GAME_DEBUG
                Debug.Log("Adding Notification (" + notification + ") as new key with for object: " + method.Target);
                #endif

                List<NotificationMethod> list = new List<NotificationMethod>();
                list.Add(method);
                dictionary.Add(notification, list);
            }
        }

        /// <summary>
        /// Registers a specific Method to a given Notification.
        /// </summary>
        /// <param name="notification">The Notification to be broadcast.</param>
        /// <param name="observer">The observer to be removed.</param>
        public static void DeregisterObserver(Notification notification, Object observer)
        {
            if (dictionary.ContainsKey(notification))
            {
                NotificationMethod removedNotification = null;
                foreach (NotificationMethod nm in dictionary[notification])
                {
                    if (nm.Target == observer)
                    {   removedNotification = nm;   }
                }

                if (removedNotification != null)
                {   dictionary[notification].Remove(removedNotification);   }
            }
        }
        
        /// <summary>
        /// Sends out a notification to a specific set of observers
        /// </summary>
        /// <param name="notification">The Notification to broadcast.</param>
        /// <param name="sender">The sender of the notification.</param>
        public static void NotifyObservers(Notification notification, MonoBehaviour sender)
        {
            #if GAME_DEBUG
            Debug.Log(sender + " is calling Notification (" + notification + ")");
            #endif

            if (dictionary.ContainsKey(notification))
            {
                foreach (NotificationMethod method in dictionary[notification])
                {   
                    #if GAME_DEBUG
                    Debug.Log("Notification (" + notification + ") sent to " + method.Target + "." + method);
                    #endif

                    method(sender);
                }
            }
        }

        /// <summary>
        /// Clears all registered observers and notifications from Notifiers dicationary.
        /// </summary>
        public static void ClearRegisteredObservers()
        {
            dictionary.Clear();
        }
    }
}