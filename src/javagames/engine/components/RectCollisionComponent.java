package javagames.engine.components;

import javagames.engine.GameObject;
import javagames.engine.model.Vector2f;

/** A collision component used for box-shaped colliders */
public class RectCollisionComponent extends CollisionComponent {
	public RectCollisionComponent(GameObject go, Vector2f[] points) {
		super(go, points);
	}
}
