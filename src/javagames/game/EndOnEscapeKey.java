package javagames.game;

import java.awt.event.KeyEvent;

import javagames.Level;
import javagames.engine.InputManager;
import javagames.engine.interfaces.InputListener;

public class EndOnEscapeKey implements InputListener {
	/**
	 * @see javagames.engine.interfaces.InputListener#processInput(float)
	 * This object checks if the escape key has been
	 * pressed and signals the main program to stop
	 * running
	 */
	@Override
	public void processInput(float delta) {
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_ESCAPE)) {
			Level.setRunning(false);
		}
	}
}
