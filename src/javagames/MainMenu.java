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

public class MainMenu extends Level{
	
	public MainMenu(Main main) {
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

	private int selected = 0;

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		
		if(selected == 0)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Story Mode", Screen.width/2-85, Screen.height/2 - 40);
		
		if(selected == 1)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Endless Mode", Screen.width/2-100, Screen.height/2 + 60);
		
		if(selected == 2)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.darkGray);
		
		g.drawString("Instructions", Screen.width/2-90, Screen.height/2 + 160);
	}

	@Override
	public void processInput(float delta) {
		super.processInput(delta);
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_DOWN)) {
			selected = (selected + 1) % 3;
		}
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_UP)) {
			selected -= 1;
			if(selected < 0)
				selected = 2;
		}
		
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_ENTER)) {
			if(selected == 0 ) {
				loadStoryMenu();
			}
		}	
	}
}
