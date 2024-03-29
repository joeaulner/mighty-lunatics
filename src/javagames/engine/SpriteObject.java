package javagames.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javagames.engine.components.CollisionComponent;
import javagames.engine.interfaces.Collider;
import javagames.engine.interfaces.IComponent;
import javagames.engine.model.Matrix3x3f;
import javagames.engine.model.Transform;
import javagames.engine.model.Vector2f;

public class SpriteObject implements GameObject, Collider {
	protected BufferedImage sprite = null;
	protected BufferedImage spritesheet = null;
	protected IComponent[] comps = new IComponent[0];
	protected Transform transform = new Transform();
	protected Matrix3x3f matrix = new Matrix3x3f();
	private Color color = Color.BLACK;

	/** Processes any components defined in the IComponent array and generates matrix for drawing */
	@Override
	public void updateWorld(float delta) {
		for (IComponent comp : comps) {
			comp.updateWorld(delta);
		}
		
		matrix = transform.generateMatrix();
	}

	/** Draws Sprite with options drawing for colliders */
	protected boolean show_colliders = false;
	
	@Override
	public void render(Graphics g) {
		g.setColor(color);
		
		// Draw Sprite
		if (sprite != null) {
			doAffineTransform((Graphics2D) g);
		}
		
		// Draw Collider
		if (show_colliders) {
			CollisionComponent cc = getCollisionComponent();
			g.setColor(Color.GREEN);
			
			if (cc != null) {
				drawVector(g, cc.getCollisionPoints());	
				for (CollisionComponent innercc : cc.getInnerColliders()) {
					drawVector(g, innercc.getCollisionPoints());
				}
			}
		}
	}
	
	private AffineTransform createTransform(Vector2f position, float angle) {
		AffineTransform transform = AffineTransform.getTranslateInstance(position.x, position.y);
		transform.rotate(angle);
		transform.scale(getTransform().getScale().x, getTransform().getScale().y);
		transform.translate(-sprite.getWidth() / 2, -sprite.getHeight() / 2);
		return transform;
	}
	
	/**
	 *	Test given code 
	 */
	private void doAffineTransform(Graphics2D g2d) {
		AffineTransform tranform = createTransform(getTransform().getPosition(), getTransform().getRotation());
		g2d.drawImage(sprite, tranform, null);
	}
	
	/** A method specific for drawing the translated points of a polygon */
	private void drawVector(Graphics g, Vector2f[] points) {
		for (int i = 0; i < points.length; ++i) {
			Vector2f v1 = new Vector2f(points[i]);
			Vector2f v2 = new Vector2f(points[(i+1) % points.length]);
			g.drawLine((int)v1.x, (int)v1.y, (int)v2.x, (int)v2.y);
		}
	}
	
	/** Runs input for any components within the IComponent Array and
	 * 	checks for possible*/
	@Override
	public void processInput(float delta) {
		for (IComponent comp : comps) {
			comp.processInput(delta);
		}
		
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_B)) {
			System.out.println("Colliders Switched");
			show_colliders = !show_colliders;
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
	
	/** Loads the given file name into a sprite data member */
	protected final void loadFile(String fileName) {
		try {
			spritesheet = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println((new File(fileName)).getAbsolutePath());
			e.printStackTrace();
			spritesheet = null;
		}
	}
	
	/** Used for subclasses of the sprite object, should be
	 *  called upon when a collision is detected*/
	@Override
	public void collisionWith(GameObject gOther) {	}
	
	/** Gets the collision component of the Sprite object, if one exists
	 *  otherwise null. */
	@Override
	public CollisionComponent getCollisionComponent() {
		for (IComponent comp : comps) {
			if (comp instanceof CollisionComponent) {
				return (CollisionComponent)comp;
			}
		}
		
		return null;
	}
}
