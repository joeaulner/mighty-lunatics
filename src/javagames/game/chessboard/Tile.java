package javagames.game.chessboard;

import javagames.engine.SpriteObject;

public class Tile extends SpriteObject {

	private Chessman piece;
	
	
    public Chessman getPiece() {
    	return this.piece;
    }
    
    @Override
    public void updateWorld(float delta) {
    	super.updateWorld(delta);
    	
    	if (inProximity()) {
    		this.piece.stopMoving();
    	}
    }
    
    public void setPiece(Chessman piece) {
        this.piece = piece;

        if (this.piece != null) {
            this.piece.moveTo(this.getTransform().getPosition());
            /*this.piece.goTransform.parent = this.goTransform;*/
            //StartCoroutine(ChessmanValidation());
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
