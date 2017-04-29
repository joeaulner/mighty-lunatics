package javagames;

import javagames.engine.GameObject;
import javagames.engine.interfaces.InputListener;
import javagames.game.CursorObject;
import javagames.game.EndOnEscapeKey;
import javagames.game.managers.BoardManager;

public class GameplayLevel extends Level {

	public GameplayLevel(Main main) {
		super(main);
		
		listeners = new InputListener[] {
			new EndOnEscapeKey()
		};
		
		gObjects = new GameObject[] {
			BoardManager.getInstance(),
			new CursorObject()
		};
	}

}
