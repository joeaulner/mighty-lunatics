package javagames.game.managers;

import javagames.game.util.Notification;
import javagames.game.util.Notifier;

public class PlayerData {
	private int score = 0;              // The player's current score.
    private int remainingMoves = 1;    // The player's remaining moves.
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
		Notifier.notifyObservers(Notification.Score_Did_Change, this);
	}
	
	public int getRemainingMoves() {
		return remainingMoves;
	}
	
	public void setRemainingMoves(int remainingMoves) {
		this.remainingMoves = remainingMoves;
        Notifier.notifyObservers(Notification.Score_Did_Change, this);

        if (this.remainingMoves <= 0) {
        	Notifier.notifyObservers(Notification.Level_Ended, this);
        }
	}
    
}
