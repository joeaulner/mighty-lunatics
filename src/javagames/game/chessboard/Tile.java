package javagames.game.chessboard;

import javagames.engine.SpriteObject;
import javagames.game.structs.Index2D;

public class Tile extends SpriteObject {

	private Chessman piece;
	private Index2D index;
	
	
    public Chessman getPiece() {
    	return this.piece;
    }

	public void setPiece(Chessman piece) {
		this.piece = piece;

		if (this.piece != null) {
			this.piece.moveTo(this.getTransform().getPosition());
            /*this.piece.goTransform.parent = this.goTransform;*/
			//StartCoroutine(ChessmanValidation());
		}
	}

	public Index2D getIndex() {
		return index;
	}

	public void setIndex(Index2D index) {
		this.index = index;
	}

	public void clear() {
//		Notifier.NotifyObservers(Notification.Chessman_Deleted, this.Piece);
		this.piece = null;
//		Notifier.NotifyObservers(Notification.Tile_Is_Empty, this);
	}

	@Override
    public void updateWorld(float delta) {
    	super.updateWorld(delta);
    	
    	if (inProximity()) {
    		this.piece.stopMoving();
    	}
    }

	private boolean inProximity() {
		if (this.piece == null) {
			return false;
		}
		
		float x = this.piece.getTransform().getPosition().x = this.getTransform().getPosition().x;
		float y = this.piece.getTransform().getPosition().y = this.getTransform().getPosition().y;
		float distance = x*x + y*y;
		
		return (distance < 2);
	}
}
