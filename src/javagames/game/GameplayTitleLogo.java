package javagames.game;

import java.awt.Graphics;

import javagames.engine.SpriteObject;
import javagames.engine.util.Screen;

public class GameplayTitleLogo extends SpriteObject {
	public GameplayTitleLogo() {
		loadFile("imgs/TitleImage.png");
		sprite = spritesheet;
	}
	
	@Override
	public void render(Graphics g) {
		int base_x = Screen.width/2 - sprite.getWidth()/4;
		g.drawImage(sprite, base_x, 30, base_x + sprite.getWidth()/2, sprite.getHeight()/2, 0, 0, sprite.getWidth(), sprite.getHeight(), null);
	}
}
