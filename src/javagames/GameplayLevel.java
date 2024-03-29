package javagames;

import javagames.engine.GameObject;
import javagames.engine.interfaces.InputListener;
import javagames.game.ChallengerSprite;
import javagames.game.CursorObject;
import javagames.game.EndOnEscapeKey;
import javagames.game.GameplayBackgroundSprite;
import javagames.game.GameplayTitleLogo;
import javagames.game.managers.BoardManager;
import javagames.game.managers.Master;
import javagames.game.managers.UIManager;
import javagames.game.util.Notifier;

public class GameplayLevel extends Level {

	public GameplayLevel(Main main, String competitor) {
		super(main);
		
		Notifier.clearRegisteredObservers();
		
		listeners = new InputListener[] {
			new EndOnEscapeKey()
		};
		
		if (!competitor.trim().isEmpty()) {
			gObjects = new GameObject[] {
				new GameplayBackgroundSprite(),
				new GameplayTitleLogo(),
				new ChallengerSprite(competitor),
				BoardManager.resetInstance(),
				new CursorObject(),
				new Master(),
				new UIManager()
			};
		} else {
			gObjects = new GameObject[] {
				new GameplayBackgroundSprite(),
				new GameplayTitleLogo(),
				BoardManager.resetInstance(),
				new CursorObject(),
				new Master(),
				new UIManager()
			};
		}
	}
}
