package javagames.game.managers;

import java.awt.Font;
import java.awt.Graphics;

import javagames.engine.GameObject;
import javagames.engine.model.Transform;
import javagames.engine.util.Screen;
import javagames.game.util.Notification;
import javagames.game.util.NotificationDelegate;
import javagames.game.util.Notifier;

public class UIManager implements NotificationDelegate, GameObject {
	private Integer score = 0;
	private Integer remainingTurns = 30;
	
	public UIManager() {
		Notifier.registerObserver(Notification.Score_Did_Change, this);
	}

	@Override
	public void notificationMethod(Notification notification, Object sender) {
		switch (notification) {
			case Score_Did_Change: updateDisplay(sender); break;
			default: break;
		}
	}

	private void updateDisplay(Object sender) {
		PlayerData pd = (PlayerData)sender;
		
		score = pd.getScore();
		remainingTurns = pd.getRemainingMoves();
	}

	@Override
	public void updateWorld(float delta) {
	}

	@Override
	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(String.format("Score: %04d Turns: %02d", score, remainingTurns), Screen.width/2 - 100, 150);
	}

	@Override
	public void processInput(float delta) {
	}

	@Override
	public Transform getTransform() {
		// TODO Auto-generated method stub
		return null;
	}
}
