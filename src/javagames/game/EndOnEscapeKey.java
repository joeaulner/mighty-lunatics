package javagames.game;

import java.awt.event.KeyEvent;

import javagames.Application;
import javagames.engine.InputManager;
import javagames.engine.interfaces.InputListener;

public class EndOnEscapeKey implements InputListener {
	/**
	 * @see javagames.engine.InputLisenter#processInput(float)
	 * This object checks if the escape key has been
	 * pressed and signals the main program to stop
	 * running
	 */
	@Override
	public void processInput(float delta) {
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_ESCAPE)) {
			Application.setRunning(false);
		}
	}
}
