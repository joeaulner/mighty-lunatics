package javagames.engine.model;

public class Transform {
	private Vector2f velocity	= new Vector2f(0, 0);
	private Vector2f position	= new Vector2f(0, 0);
	private Vector2f scale		= new Vector2f(1, 1);
	private float rotation		= 0.0f;
	
	public void translate(Vector2f vector) {
		this.position.x += vector.x;
		this.position.y += vector.y;
	}
	
	/**
	 * Returns the velocity
	 * @return the velocity
	 */
	public Vector2f getVelocity() {
		return velocity;
	}

	/**
	 * Sets the velocity
	 * @param velocity - new velocity of the Transform
	 */
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	/**
	 * Returns the position
	 * @return the position
	 */
	public Vector2f getPosition() {
		return this.position;
	}

	/**
	 * Sets the position
	 * @param position - new position of the Transform
	 */
	public void setPosition(Vector2f position) {
		this.position = position;
	}

	/**
	 * Returns the rotation
	 * @return the rotation
	 */
	public float getRotation() {
		return rotation;
	}

	/**
	 * Sets the rotation
	 * @param rotation - new rotation of the Transform
	 */
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	/**
	 * Returns the scale
	 * @return the scale
	 */
	public Vector2f getScale() {
		return scale;
	}

	/**
	 * Sets the scale
	 * @param scale - the new scale of the Transform
	 */
	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	/**
	 * Creates a matrix for the other classes to utilize when
	 * rendering to the screen.
	 * @return A matrix of relative modification necessary to be
	 * 			made to a vector to properly scale, rotate, and translate
	 */
	public Matrix3x3f generateMatrix() {
		Matrix3x3f mat = Matrix3x3f.identity();
		mat = mat.mul(Matrix3x3f.scale(scale));
		mat = mat.mul(Matrix3x3f.rotate(this.rotation/* + 3.1415f*/));
		mat = mat.mul(Matrix3x3f.translate(this.position));
//		mat = mat.mul(World.getWorldToScreenMatrix());
		
		return mat;
	}
}
