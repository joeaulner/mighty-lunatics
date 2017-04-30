package javagames.game.managers;

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

    
    boolean readyToRestart = false;
    private void endLevel(Object sender) {
    	BoardManager.clearInstance();
    	readyToRestart = true;
//    	Level.restart();
    }

	@Override
	public void updateWorld(float delta) {
		
	}

	@Override
	public void render(Graphics g) {
		if (readyToRestart) {
			g.drawString("Press R To Restart the Level", Screen.width/2, Screen.height/2);
		}
	}

	@Override
	public void processInput(float delta) {
		if (readyToRestart && InputManager.getInputManager().keyDownOnce(KeyEvent.VK_R)) {
			Level.restart();
		}
	}

	@Override
	public Transform getTransform() {
		return null;
	}
}
