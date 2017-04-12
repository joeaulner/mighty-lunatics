package javagames.engine.interfaces;

public interface InputListener {
	/**
	 * Called by the main game loop for all "listeners"
	 * to check potential user input on the mouse or
	 * keyboard (low-level logic)
	 * @param delta TODO
	 */
	public void processInput(float delta);
}
