package javagames.engine.components;

import javagames.engine.GameObject;
import javagames.engine.model.Matrix3x3f;
import javagames.engine.model.Vector2f;

/** A base class for the component utilized by the Collider Object*/
public abstract class CollisionComponent extends Component {
	private CollisionComponent[] innerColliders = null;
	private Vector2f[] colliderPoints = new Vector2f[0];
	
	public CollisionComponent(GameObject go, Vector2f[] points) {
		super(go);
		this.colliderPoints = points;
		this.innerColliders = new CollisionComponent[] {
				this
		};
	}
	
	/**
	 * Returns the array of inner colliders
	 * @return the array of inner colliders
	 */
	public CollisionComponent[] getInnerColliders() {
		return innerColliders;
	}

	/**
	 * Sets the inner colliders of the CollisionComponent
	 * @param innerColliders the inner colliders to be set to
	 */
	public void setInnerColliders(CollisionComponent[] innerColliders) {
		this.innerColliders = innerColliders;
	}
	
	/**
	 * Returns the array of collision points for this CollisionComponent
	 * @return the array of collision points for thsi CollisionComponent
	 */
	public Vector2f[] getCollisionPoints() {
		Vector2f[] translatedPoints = new Vector2f[colliderPoints.length];
		Matrix3x3f matrix = go.getTransform().generateMatrix();
		
		for (int i = 0; i < translatedPoints.length; i++) {
			translatedPoints[i] = matrix.mul(colliderPoints[i]);
		}
		
		return translatedPoints;
	}
}
