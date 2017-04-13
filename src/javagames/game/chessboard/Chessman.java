package javagames.game.chessboard;

import java.awt.Color;

import javagames.engine.model.Vector2f;
import javagames.game.structs.Index2D;
import javagames.engine.SpriteObject;

public abstract class Chessman extends SpriteObject {
	protected Color color;
	private boolean shouldMove = false;
	private Vector2f targetPosition = null;
	
	public void moveTo(Vector2f position) {
		this.targetPosition = new Vector2f(position.x, position.y);
        this.shouldMove = true;
    }
	 
	@Override
	public void updateWorld(float delta) {
		super.updateWorld(delta);
	
		if (this.shouldMove) {
			float xPosition = this.targetPosition.x - this.getTransform().getPosition().x;
			float yPosition = this.targetPosition.y - this.getTransform().getPosition().y;
			
			Vector2f direction = new Vector2f(xPosition, yPosition, 0f);
			
			this.getTransform().translate(direction.mul(0.15f));
		}
	}
	
    public abstract Index2D[] MoveablePositions();
    
    public abstract int ScoreValue();
}
