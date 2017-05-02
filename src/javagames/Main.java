package javagames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javagames.engine.util.Screen;
import javagames.engine.util.SimpleFramework;

public class Main extends SimpleFramework  {
	private static final long serialVersionUID = -2945372375316935538L;
	
	Level app;
	
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
		appTitle = "Chess Swap";
		appWorldWidth = Screen.width;
		appWorldHeight = Screen.height;
		
		loadMainMenu();
	}
	
	/**
	 * Reset Application for running in the world
	 */
	public void loadMainMenu() {
		app = new MainMenu(this);
	}
	
	public void loadStoryMenu() {
		app = new StoryMenu(this);
	}
	
	private int count = 0;
	private final String[] array = new String[] {
			"fabianocaruana",
			"garykasparov",
			"jeremysilman",
			"juditpolgar",
			"magnuscarlsen"
	};
	
	public void loadGamePlayLevel(boolean next) {
		if (next) {
			count++;
		}
		
		if (count < array.length) {
			app = new GameplayLevel(this, array[count]);
		} else {
			loadMainMenu();
			count = 0;
		}
	}
	
	public void selectLevel(String competitor) {
		app = new GameplayLevel(this, competitor);
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
