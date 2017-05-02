package javagames.game;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javagames.engine.InputManager;
import javagames.engine.VectorObject;
import javagames.engine.model.Vector2f;
import javagames.engine.util.Screen;
import javagames.game.managers.BoardManager;

public class CursorObject extends VectorObject {
	private final int HEIGHT	= 10;
	private final int WIDTH		= 10;

	private int dx = 0;
	private int dy = 0;
	
	/**
	 * Default constructor generates a cross hair shape
	 * out of points, sets the position to the middle of
	 * the screen, and sets the color to Red.
	 */
	public CursorObject() {
		this.points = new Point[] {
			new Point(0, HEIGHT),
			new Point(WIDTH, 0),
			new Point(0, -HEIGHT),
			new Point(-WIDTH, 0),
			new Point(0, HEIGHT),
			new Point(0, -HEIGHT),
			new Point(WIDTH, 0),
			new Point(-WIDTH, 0)
		};

		getTransform().setPosition(new Vector2f(Screen.width / 2, Screen.height / 2));
		getTransform().setScale(new Vector2f(1, 1));
		setColor(Color.RED);	
	}
	
	/**
	 * @see javagames.engine.InputLisenter#processInput(float)
	 * 
	 * This method tracks input related mouse movement and saves
	 * the changes into variable used for the updateWorld.
	 */
	@Override
	public void processInput (float delta) {
		dx = InputManager.getInputManager().getRelativePosition().x;
		dy = InputManager.getInputManager().getRelativePosition().y;
		
		if (InputManager.getInputManager().buttonDownOnce(MouseEvent.BUTTON1)) {
			BoardManager.getInstance().mouseClickAt(getTransform().getPosition());
		}
	}
	
	/**
	 * @see javagames.engine.Drawable#updateWorld(float)
	 * 
	 * Configures the position of the cursor
	 * on the position retrieved from processInput() 
	 * based on movement of the mouse.
	 */
	@Override
	public void updateWorld(float delta) {
		Vector2f v = new Vector2f(this.transform.getPosition());
		
		v.x += dx;
		v.y += dy;
		
		if (v.x - WIDTH > 0 && v.x + WIDTH < Screen.width && v.y - HEIGHT > 0 && v.y + HEIGHT < Screen.height) {
			this.transform.setPosition(v);
		}
		
		this.matrix = this.transform.generateMatrix();
	}
}
