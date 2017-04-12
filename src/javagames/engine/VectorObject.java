package javagames.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javagames.engine.interfaces.IComponent;
import javagames.engine.model.Matrix3x3f;
import javagames.engine.model.Transform;
import javagames.engine.model.Vector2f;

public class VectorObject implements GameObject {
	protected Point[] points = new Point[0];
	protected IComponent[] comps = new IComponent[0];
	protected Transform transform = new Transform();
	protected Matrix3x3f matrix = new Matrix3x3f();
	private Color color = Color.BLACK;
	
	/**
	 * @see javagames.engine.InputLisenter#processInput(float)
	 */
	@Override
	public void processInput(float delta) {
		for (IComponent comp : comps) {
			comp.processInput(delta);
		}
	}
	
	/**
	 * @see javagames.engine.Drawable#updateWorld(float)
	 * Updates the matrix to the current position of the
	 * VectorObject.
	 * 
	 * Subclass Note: When overriding this method, the line
	 * {@code matrix = transform.generateMatrix();} must be
	 * placed at the end of the updateWorld method
	 */
	@Override
	public void updateWorld(float delta) {
		for (IComponent comp : comps) {
			comp.updateWorld(delta);
		}
		
		matrix = transform.generateMatrix();
	}
	
	/**
	 * @see javagames.engine.Drawable#render(Graphics g)
	 * 
	 * Renders the Vector graphic based on the points collection
	 * given the transform provided and modified from this class.
	 */
	@Override
	public final void render(Graphics g) {
		g.setColor(color);
		for (int i = 0; i < points.length; ++i) {
			// Copying for modification
			Point p1 = new Point(points[i]);
			Point p2 = new Point(points[(i+1) % points.length]);
			
			// Apply Matrix Here
			Vector2f v1 = matrix.mul(new Vector2f(p1.x, p1.y));
			Vector2f v2 = matrix.mul(new Vector2f(p2.x, p2.y));
			
			// Drawing with translation
			g.drawLine((int)v1.x, (int)v1.y, (int)v2.x, (int)v2.y);
		}
	}

	/**
	 * Returns the transform of the VectorObject
	 * @return the transform of the VectorObject
	 */
	@Override
	public Transform getTransform() {
		return transform;
	}

	/**
	 * Sets the transform of the VectorObject
	 * @param transform - the new transform for this VectorObject
	 */
	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	/**
	 * Returns the drawing color of the VetorObject
	 * @return the drawing color of the VetorObject
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the drawing color of the VectorObject
	 * @param color - the Color to change draw with
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}
