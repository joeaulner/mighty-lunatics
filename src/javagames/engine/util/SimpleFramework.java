package javagames.engine.util;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javagames.Level;
import javagames.engine.InputManager;
import javagames.engine.model.Matrix3x3f;
import javagames.engine.model.Vector2f;

/**
 * Textbook Class; All modifications are marked with a comment above.
 */
public class SimpleFramework extends JFrame implements Runnable {
	private static final long serialVersionUID = 4763353200555987063L;
	
	private BufferStrategy bs;
	private Thread gameThread;
	protected FrameRate frameRate;
	protected Canvas canvas;
	
	protected Color appBackground = Color.WHITE;
	protected Color appBorder = Color.LIGHT_GRAY;
	protected Color appFPSColor = Color.GREEN;
	protected Font appFont = new Font("Courier New", Font.PLAIN, 14);
	protected String appTitle = "TBD-Title";
	protected float appBorderScale = 0.8f;
	protected int appWidth = 640;
	protected int appHeight = 640;
	protected float appWorldWidth = 640.0f;
	protected float appWorldHeight = 640.0f;
	protected long appSleep = 10L;
	protected boolean appMaintainRatio = false;

	public SimpleFramework() {
		
	}

	protected void createAndShowGUI() {
		canvas = new Canvas();
		canvas.setSize(1280, 720);
		canvas.setBackground(appBackground);
		canvas.setIgnoreRepaint(true);

		getContentPane().add(canvas);
		
		setLocationByPlatform(true);
		if (appMaintainRatio) {
			getContentPane().setBackground(appBorder);
			setSize(appWidth, appHeight);
			canvas.setSize(appWidth, appHeight); // bugfix Jan 2015
			setLayout(null);
			getContentPane().addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent e) {
					onComponentResized(e);
				}
			});
		} else {
			canvas.setSize(appWidth, appHeight);
			pack();
		}
		
		setTitle(appTitle);

		// Added key listener (InputManager)
		canvas.addKeyListener(InputManager.getInputManager());

		// Added mouse listener (InputManager)
		InputManager.initializeInputManager(canvas);
		canvas.addMouseListener(InputManager.getInputManager());
		canvas.addMouseMotionListener(InputManager.getInputManager());
		canvas.addMouseWheelListener(InputManager.getInputManager());

		setVisible(true);

		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		canvas.requestFocus();

		gameThread = new Thread(this);
		gameThread.start();
	}

	protected void onComponentResized(ComponentEvent e) {
		Dimension size = getContentPane().getSize();
		int vw = (int) (size.width * appBorderScale);
		int vh = (int) (size.height * appBorderScale);
		int vx = (size.width - vw) / 2;
		int vy = (size.height - vh) / 2;
		int newW = vw;
		int newH = (int) (vw * appWorldHeight / appWorldWidth);
		if (newH > vh) {
			newW = (int) (vh * appWorldWidth / appWorldHeight);
			newH = vh;
		}
		vx += (vw - newW) / 2;
		vy += (vh - newH) / 2;
		canvas.setLocation(vx, vy);
		canvas.setSize(newW, newH);
		Screen.height = newH;
		Screen.width = newW;
	}

	protected Matrix3x3f getViewportTransform() {
		return Utility.createViewport(appWorldWidth, appWorldHeight,
				canvas.getWidth(), canvas.getHeight());
	}

	protected Matrix3x3f getReverseViewportTransform() {
		return Utility.createReverseViewport(appWorldWidth, appWorldHeight,
				canvas.getWidth(), canvas.getHeight());
	}

	protected Vector2f getWorldMousePosition() {
		Matrix3x3f screenToWorld = getReverseViewportTransform();
		Point mousePos = InputManager.getInputManager().getRelativePosition();
		Vector2f screenPos = new Vector2f(mousePos.x, mousePos.y);
		return screenToWorld.mul(screenPos);
	}

	protected Vector2f getRelativeWorldMousePosition() {
		float sx = appWorldWidth / (canvas.getWidth() - 1);
		float sy = appWorldHeight / (canvas.getHeight() - 1);
		Matrix3x3f viewport = Matrix3x3f.scale(sx, -sy);
		Point p = InputManager.getInputManager().getRelativePosition();
		return viewport.mul(new Vector2f(p.x, p.y));
	}

	public void run() {
		Level.setRunning(true);
		initialize();

		// Create a new blank cursor.		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		this.getContentPane().setCursor(blankCursor);
		
		long curTime = System.nanoTime();
		long lastTime = curTime;
		double nsPerFrame;
		while (Level.isRunning()) {
			curTime = System.nanoTime();
			nsPerFrame = curTime - lastTime;
			gameLoop((float) (nsPerFrame / 1.0E9));
			lastTime = curTime;
		}
		
		terminate();
	}

	protected void initialize() {
		frameRate = new FrameRate();
		frameRate.initialize();
	}

	protected void terminate() {
		this.dispose();
		System.exit(0);
	}

	private void gameLoop(float delta) {
		processInput(delta);
		updateObjects(delta);
		renderFrame();
		sleep(appSleep);
	}

	private void renderFrame() {
		do {
			do {
				Graphics g = null;
				try {
					g = bs.getDrawGraphics();
					g.clearRect(0, 0, getWidth(), getHeight());
					render(g);
				} finally {
					if (g != null) {
						g.dispose();
					}
				}
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	private void sleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException ex) {
		}
	}

	protected void processInput(float delta) {
		World.setWorldToScreenMatrix(getViewportTransform());
	}

	protected void updateObjects(float delta) {
	}

	protected void render(Graphics g) {
		g.setFont(appFont);
		g.setColor(appFPSColor);
		frameRate.calculate();
		g.drawString(frameRate.getFrameRate(), 20, 20);
	}

	protected void onWindowClosing() {
		Level.setRunning(false);
		this.dispose();
		System.exit(0);
	}

	protected static void launchApp(final SimpleFramework app) {
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.onWindowClosing();
			}
		});
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				app.createAndShowGUI();
			}
		});
	}
}