package javagames.game.sound;

public class SoundException extends RuntimeException {
	private static final long serialVersionUID = -2824856894087843480L;

	public SoundException(String message) {
		super(message);
	}

	public SoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}