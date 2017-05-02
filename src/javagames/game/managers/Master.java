package javagames.game.managers;

import java.awt.Font;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

import javagames.Level;
import javagames.engine.GameObject;
import javagames.engine.InputManager;
import javagames.engine.model.Transform;
import javagames.engine.util.Screen;
import javagames.game.chessboard.Chessman;
import javagames.game.util.Notification;
import javagames.game.util.NotificationDelegate;
import javagames.game.util.Notifier;

public class Master implements NotificationDelegate, GameObject {
	private PlayerData pData = new PlayerData();
	
	public Master() {
		Notifier.registerObserver(Notification.Player_Did_Move, this);
        Notifier.registerObserver(Notification.Chessman_Deleted, this);
        Notifier.registerObserver(Notification.Level_Ended, this);
	}

	@Override
	public void notificationMethod(Notification notification, Object sender) {
		switch (notification) {
			case Player_Did_Move: decrementMoveCount(sender); break;
			case Chessman_Deleted: updateScore(sender); break;
			case Level_Ended: endLevel(sender); break;
				
			default: break;
		}
	}
	
	private void decrementMoveCount(Object sender) {
        this.pData.setRemainingMoves(this.pData.getRemainingMoves() - 1);
    }
	
    private void updateScore(Object sender) {
        this.pData.setScore(this.pData.getScore() + ((Chessman) sender).scoreValue());
    }

    
    boolean gameOver = false;
    private void endLevel(Object sender) {
    	BoardManager.clearInstance();
    	gameOver = true;
    }

	@Override
	public void updateWorld(float delta) {
		// Nothing
	}

	@Override
	public void render(Graphics g) {
		if (gameOver) {
			g.setFont(new Font("Arial", Font.BOLD, 30));
			
			if (pData.getScore() >= 250) {
				g.drawString("You Have Defeated Your Opponent", Screen.width/2 - 225, Screen.height/2 - 40);
				g.drawString("Press Enter to Proceed", Screen.width/2 - 150, Screen.height/2 + 40);
			} else {
				g.drawString("You Have Lost", Screen.width/2 - 90, Screen.height/2 - 40);
			}
			
			g.drawString("Press R To Replay the Level", Screen.width/2 - 190, Screen.height/2);
		}
	}

	@Override
	public void processInput(float delta) {
		if (gameOver) {
			if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_R)) {
				Level.reloadLevel();
			} else if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_ENTER)) {
				Level.loadNextLevel();
			}
		}
	}

	@Override
	public Transform getTransform() {
		return null;
	}
}
