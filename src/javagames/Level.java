package javagames;

import java.awt.Color;
import java.awt.Graphics;

import javagames.engine.GameObject;
import javagames.engine.InputManager;
import javagames.engine.interfaces.InputListener;

public class Level {
	private static volatile boolean isRunning = true;
	private static Main delegate;
	
	protected InputListener[] listeners;
	
	protected GameObject[] gObjects;
	
	/**
	 * Set up the components of the game each new instance of Application
	 * @param delegate - The Sprite object running the program
	 */
	public Level(Main main) {
		delegate = main;
	}
	
	/**
	 * Returns the running status of the application
	 * @return the running status of the application
	 */
	public static boolean isRunning() {
		return isRunning;
	}

	/**
	 * Sets the status of the application
	 * @param running - new status of the application
	 */
	public static void setRunning(boolean running) {
		isRunning = running;
	}
	
	public static void restart() {
		delegate.restart();
	}
	
	float timeDelay = 0;
	
	/** 
	 * Caches the input and call all InputListeners and
	 * GameObjects to perform actions related to processing
	 * input
	 */
	public void processInput(float delta) {
		InputManager.poll();
		
		for (InputListener il : listeners) {
			il.processInput(delta);
		}
		
		for (GameObject go : gObjects) {
			go.processInput(delta);
		}
	}
	
	/**
	 * Cycles through every GameObjects updateWorld
	 */
	public void update(float delta) {
		for (GameObject go : gObjects) {
			go.updateWorld(delta);
			
			if (go != null) {
				checkCollisions(go);
			}
		}
	}
	
	private void checkCollisions(GameObject go) {
		/*for (GameObject gOther : gObjects) {
			if (go instanceof Collider) {
				Collider c1 = ((Collider)go);
				CollisionComponent cc1 = c1.getCollisionComponent();
				
				if (cc1 != null) {
				if (go != gOther && gOther instanceof Collider) {
					Collider c2 = ((Collider)gOther);
					CollisionComponent cc2 = c2.getCollisionComponent();
					if (cc2 != null) {
					if (CollisionChecker.rectRectIntersection(cc1.getCollisionPoints(), cc2.getCollisionPoints())) {
						for (CollisionComponent colComp1 : cc1.getInnerColliders()) {
							if (CollisionChecker.rectRectIntersection(colComp1.getCollisionPoints(), cc2.getCollisionPoints())) {
								c1.collisionWith(gOther);
								c2.collisionWith(go);
								break;
							}
						}
					}
					}
				}
				}
			}
		}*/
	}

	/**
	 * Cycles through rendering each GameObject
	 * through the related render method. The screen
	 * Graphics color is reset to Black and the canvas
	 * is then passed of to the GameObjects to render
	 * @param g - Graphics to render to
	 */
	public void render(Graphics g) {
		for (GameObject go : gObjects) {
			g.setColor(Color.BLACK);
			go.render(g);
		}
	}
}
