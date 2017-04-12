package javagames.engine.interfaces;

import java.awt.Graphics;

public interface Drawable {
	/**
	 * This method is called in the main game loop on
	 * all objects after processInput has been called.
	 * All heavy game logic (transformations, automated
	 * actions, etc) should occur here.
	 * @param delta TODO
	 */
	public void updateWorld(float delta);
	
	/**
	 * Render the object graphically after input has
	 * been processed and the world has been updated
	 * with any game logic. 
	 * @param g - screen to render upon
	 */
	public void render(Graphics g);
}
