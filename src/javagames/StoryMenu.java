package javagames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javagames.engine.GameObject;
import javagames.engine.InputManager;
import javagames.engine.interfaces.InputListener;
import javagames.engine.util.Screen;
import javagames.game.EndOnEscapeKey;
import javagames.game.GameplayBackgroundSprite;
import javagames.game.GameplayTitleLogo;
import javagames.game.util.Notifier;

public class StoryMenu extends Level{
	private int selected = 0;

	public StoryMenu(Main main) {
		super(main);
		Notifier.clearRegisteredObservers();
		
		listeners = new InputListener[] {
			new EndOnEscapeKey()
		};
		gObjects = new GameObject[] {
				new GameplayBackgroundSprite(),
				new GameplayTitleLogo(),
		};
	}

	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		
		if(selected == 0)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Fabiano Caruana", Screen.width/2-90, Screen.height/2 - 40);
		
		if(selected == 1)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Gary Kasparov", Screen.width/2-90, Screen.height/2);
		
		if(selected == 2)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Jeremy Silman", Screen.width/2-90, Screen.height/2 + 40);
		
		if(selected == 3)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Judit Polgar", Screen.width/2-90, Screen.height/2 + 80);
		
		if(selected == 4)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Magnus Carlsen", Screen.width/2-90, Screen.height/2 + 120);
	}
	
	public void processInput(float delta) {
		super.processInput(delta);
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_DOWN)) {
			selected = (selected + 1) % 5;
		}
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_UP)) {
			selected -= 1;
			if(selected < 0)
				selected = 4;
		}
		
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_ENTER)) {
			if(selected == 0) {
				selectLevel("garykasparov");
			}else if(selected == 1) {
				selectLevel("fabianocaruana");
			}
			else if(selected == 2) {
				selectLevel("jeremysilman");
			}
			else if(selected == 3) {
				selectLevel("juditpolgar");
			}
			else if(selected == 4) {
				selectLevel("magnuscarlsen");
			}
		}
	}
}
