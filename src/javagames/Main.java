package javagames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javagames.engine.util.Screen;
import javagames.engine.util.SimpleFramework;

public class Main extends SimpleFramework  {
	private static final long serialVersionUID = -2945372375316935538L;
	
	Application app;
	
	/**
	 * Setup the game for appropriate screen sizing
	 */
	public Main() {
		appBackground = Color.WHITE;
		appBorder = Color.LIGHT_GRAY;
		appFont = new Font("Courier New", Font.PLAIN, 14);
		appBorderScale = 0.95f;
		appFPSColor = Color.BLACK;
		
		Screen.width = (int)((appWidth = 1152) * appBorderScale * appBorderScale);
		Screen.height = (int)((appHeight = 648) * appBorderScale * appBorderScale);
		
		appMaintainRatio = true;
		appSleep = 10L;
		appTitle = "Sprites";
		appWorldWidth = Screen.width;
		appWorldHeight = Screen.height;
		
		app = new Application(this);
	}
	
	/**
	 * Reset Application for running in the world
	 */
	public void restart() {
		app = new Application(this);
	}

	@Override
	protected void initialize() {
		super.initialize();
	}

	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		app.processInput(delta);
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		app.update(delta);
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		app.render(g);
	}

	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new Main());
	}
}
