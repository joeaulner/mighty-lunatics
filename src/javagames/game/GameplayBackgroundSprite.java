package javagames.game;

import java.awt.Graphics;

import javagames.engine.SpriteObject;
import javagames.engine.util.Screen;

public class GameplayBackgroundSprite extends SpriteObject {
	public GameplayBackgroundSprite() {
		loadFile("imgs/ChessSwapBackground.png");
		sprite = spritesheet;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, 0, 0, Screen.width, Screen.height, 0, 0, sprite.getWidth(), sprite.getHeight(), null);
	}
}
