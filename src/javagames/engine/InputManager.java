package javagames.engine;

import java.awt.Component;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

public class InputManager implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	private static InputManager im;
	private static Component display;

	private static Point mousePos;
	private static Point currentPos;
	private static boolean[] mouseButtons;
	private static int[] polledButton;
	private static int notches;
	private static int polledNotches;

	private static int dx, dy;
	private static Robot robot;

	/**
	 * Singleton Pattern getter
	 * @return a Singleton of the InputManagerClass
	 */
	public static InputManager getInputManager() {
		return (im != null) ? im : (im = new InputManager());
	}

	/**
	 * Merged constructor and canvas setup from KeyboardInput and
	 * RelativeMouseInput
	 * 
	 * @param component - component for the InputManager to apply
	 * mouse operations to
	 */
	public static void initializeInputManager(Component component) {
		keys = new boolean[256];
		polledKeys = new int[256];

		display = component;
		try {
			robot = new Robot();
		} catch (Exception e) {
			// Handle exception [game specific]
			e.printStackTrace();
		}

		mousePos = new Point(0, 0);
		currentPos = new Point(0, 0);
		mouseButtons = new boolean[BUTTON_COUNT];
		polledButton = new int[BUTTON_COUNT];
	}

	/**
	 * Merged the poll() method of RelativeMouseInput and KeyboardInput
	 */
	public static synchronized void poll() {
		for (int i = 0; i < keys.length; ++i) {
			if (keys[i]) {
				polledKeys[i]++;
			} else {
				polledKeys[i] = 0;
			}
		}

		mousePos = new Point(dx, dy);
		currentPos.translate(dx, -dy);
		dx = dy = 0;

		polledNotches = notches;
		notches = 0;

		for (int i = 0; i < mouseButtons.length; ++i) {
			if (mouseButtons[i]) {
				polledButton[i]++;
			} else {
				polledButton[i] = 0;
			}
		}
	}

	// #################
	//
	// KeyBoard Listener (Unchanged from KeyboardInput)
	//
	// #################
	private static boolean[] keys;
	private static int[] polledKeys;

	static {
	}

	public boolean keyDown(int keyCode) {
		return polledKeys[keyCode] > 0;
	}

	public boolean keyDownOnce(int keyCode) {
		return polledKeys[keyCode] == 1;
	}

	public synchronized void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < keys.length) {
			keys[keyCode] = true;
		}
	}

	public synchronized void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode >= 0 && keyCode < keys.length) {
			keys[keyCode] = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		// Not needed
	}

	// #####################
	//
	// MouseListener (Unchanged from RelativeMouseInput)
	//
	// #####################
	private static final int BUTTON_COUNT = 3;

	public Point getRelativePosition() {
		return mousePos;
	}

	public Point getCurrentPosition() {
		return currentPos;
	}

	public int getNotches() {
		return polledNotches;
	}

	public boolean buttonDown(int button) {
		return polledButton[button - 1] > 0;
	}

	public boolean buttonDownOnce(int button) {
		return polledButton[button - 1] == 1;
	}

	public synchronized void mousePressed(MouseEvent e) {
		int button = e.getButton() - 1;
		if (button >= 0 && button < mouseButtons.length) {
			mouseButtons[button] = true;
		}
	}

	public synchronized void mouseReleased(MouseEvent e) {
		int button = e.getButton() - 1;
		if (button >= 0 && button < mouseButtons.length) {
			mouseButtons[button] = false;
		}
	}

	public void mouseClicked(MouseEvent e) {
		// Not needed
	}

	public synchronized void mouseEntered(MouseEvent e) {
		mouseMoved(e);
	}

	public synchronized void mouseExited(MouseEvent e) {
		mouseMoved(e);
	}

	public synchronized void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	public synchronized void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		Point center = getComponentCenter();
		dx += p.x - center.x;
		dy += p.y - center.y;
		centerMouse();
	}

	public synchronized void mouseWheelMoved(MouseWheelEvent e) {
		notches += e.getWheelRotation();
	}

	private Point getComponentCenter() {
		int w = display.getWidth();
		int h = display.getHeight();
		return new Point(w / 2, h / 2);
	}

	private void centerMouse() {
		if (robot != null && display.isShowing()) {
			Point center = getComponentCenter();
			SwingUtilities.convertPointToScreen(center, display);
			robot.mouseMove(center.x, center.y);
		}
	}
}
