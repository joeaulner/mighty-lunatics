package javagames.game.managers;

import javagames.Application;
import javagames.game.chessboard.Chessman;
import javagames.game.util.Notification;
import javagames.game.util.NotificationDelegate;
import javagames.game.util.Notifier;

public class Master implements NotificationDelegate {
	private PlayerData pData;
	
	public Master() {
		Notifier.registerObserver(Notification.Player_Did_Move, this);
        Notifier.registerObserver(Notification.Chessman_Deleted, this);
        Notifier.registerObserver(Notification.Level_Ended, this);
	}

	@Override
	public void notificationMethod(Notification notification, Object sender) {
		switch (notification) {
			case Tile_Is_Empty: decrementMoveCount(sender); break;
			case Tile_Is_Selected: updateScore(sender); break;
			case Tile_Stopped_Chessman: endLevel(sender); break;
				
			default: break;
		}
	}
	
	private void decrementMoveCount(Object sender) {
        this.pData.setRemainingMoves(this.pData.getRemainingMoves() - 1);
    }
	
    private void updateScore(Object sender) {
        this.pData.setScore(this.pData.getScore() + ((Chessman) sender).scoreValue());
    }

    private void endLevel(Object sender) {
    	// TODO: Change to What we really want later
    	Application.restart();
    }
}
