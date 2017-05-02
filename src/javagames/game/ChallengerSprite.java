package javagames.game;

import javagames.engine.SpriteObject;
import javagames.engine.model.Vector2f;

public class ChallengerSprite extends SpriteObject {
	public ChallengerSprite(String name) {
		getTransform().setPosition(new Vector2f(360, 100));
		loadFile("imgs/" + name + ".png");
		sprite = spritesheet;
	}
}
