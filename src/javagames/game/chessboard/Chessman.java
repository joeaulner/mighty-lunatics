package javagames.game.chessboard;

import java.awt.Graphics;

import javagames.engine.SpriteObject;
import javagames.engine.model.Vector2f;
import javagames.game.structs.Index2D;

public abstract class Chessman extends SpriteObject {
	private Color color;
	private boolean shouldMove = false;
	private Vector2f targetPosition = null;
	private String name;

	public Chessman(String name) {
		this.name = name;
		
		if (name.contains("Black")) {
			setColor(Color.Black);
		} else if (name.contains("White")) {
			setColor(Color.White);
		}
		

		getTransform().setScale(new Vector2f(0.2f, 0.2f));
		
		if (name != null) {
			loadFile("imgs/chesspieces/" + name + ".png");
			sprite = spritesheet;
		}
	}

	public String getName() {
		return name;
	}

	public void moveTo(Vector2f position) {
		this.targetPosition = new Vector2f(position.x, position.y);
		this.shouldMove = true;
	}
	
	public void stopMoving() {
		this.shouldMove = false;
	}

	@Override
	public void updateWorld(float delta) {
		super.updateWorld(delta);
	
		if (this.shouldMove) {
			float xPosition = this.targetPosition.x - this.getTransform().getPosition().x;
			float yPosition = this.targetPosition.y - this.getTransform().getPosition().y;
			
			Vector2f direction = new Vector2f(xPosition, yPosition);
			
			this.getTransform().translate(direction.mul(0.05f));
		}
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if (sprite != null) {
//			System.out.println("TEST CHESSMAN");
//			System.out.println(getTransform().getPosition().x + ", " + getTransform().getPosition().y);
		}
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Returns all possible movement positions.
	 * @return An array of potential movement Positions (2D).
	 */
    public abstract Index2D[] moveablePositions();

	/**
	 * Returns the point value when three of this piece are matched.
	 * @return The point value of this Chessman object.
	 */
    public abstract int scoreValue();
}
