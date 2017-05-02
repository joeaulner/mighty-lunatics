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
	
	public static void loadMainMenu() {
		delegate.loadMainMenu();
	}
	
	public static void reloadLevel() {
		delegate.loadGamePlayLevel(false);
	}
	
	public static void loadNextLevel() {
		delegate.loadGamePlayLevel(true);
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
		}
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
