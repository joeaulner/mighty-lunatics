package javagames.game.util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Notifier {
	private static Map<Notification, List<NotificationDelegate>> map
		= new HashMap<Notification, List<NotificationDelegate>>();
	
	public static void registerObserver(Notification notification, NotificationDelegate delegate) {
        if (map.containsKey(notification)) {
            if (!map.get(notification).contains(delegate)) {
            	map.get(notification).add(delegate);
            }
        } else {
            List<NotificationDelegate> list = new LinkedList<NotificationDelegate>();
            list.add(delegate);
            map.put(notification, list);
        }
    }
	
	public static void deregisterObserver(Notification notification, NotificationDelegate delegate) {
        if (map.containsKey(notification)) {
        	NotificationDelegate removedNotification = null;
            
        	for (NotificationDelegate nd : map.get(notification)) {
                if (nd.equals(delegate)) {
                	removedNotification = nd;
                }
            }

            if (removedNotification != null) {
            	map.get(notification).remove(removedNotification);
            }
        }
    }
	
	public static void notifyObservers(Notification notification, Object sender) {
		if (map.containsKey(notification)) {
            for (NotificationDelegate delegate : map.get(notification)) {
            	delegate.notificationMethod(notification, sender);
            }
        }
    }
	
	public static void ClearRegisteredObservers() {
        map.clear();
    }
}