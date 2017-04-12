package javagames.engine.interfaces;

import javagames.engine.GameObject;
import javagames.engine.components.CollisionComponent;

/** Interface for any Object with can be collided into by another
 *  Collider Object*/
public interface Collider {
	public void collisionWith(GameObject gOther);

	public CollisionComponent getCollisionComponent();
}
