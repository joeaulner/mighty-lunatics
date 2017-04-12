package javagames.game;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javagames.engine.InputManager;
import javagames.engine.SpriteObject;
import javagames.engine.components.Component;
import javagames.engine.components.RectCollisionComponent;
import javagames.engine.model.Vector2f;
import javagames.engine.util.Screen;

public class AnimatedObject extends SpriteObject {
	public AnimatedObject() {
		getTransform().setPosition(new Vector2f(Screen.width / 2, Screen.height / 2));
		getTransform().setScale(new Vector2f(2f, 2f));
		loadFile("player.png");
		
		animation = getAnimations(10, 3, 48, 48, 9, 14);
		animation.addAll(getAnimations(11, 1, 48, 48, 9, 14));
		animation.addAll(getAnimations(9, 1, 48, 48, 9, 14));
		
		this.comps = new Component[] {
			new RectCollisionComponent(this, new Vector2f[] {
				new Vector2f(-12, 24),
				new Vector2f(12, 24),
				new Vector2f(12, -12),
				new Vector2f(-12, -12)
			})
		};
	}
	
	/**
	 *	Grabs a collection of nearby sprites from the spritesheet and processes
	 *	them for use as an animation set.
	 */
	private List<BufferedImage> getAnimations(int index, int length, int width, int height, int swidth, int sheight) {
		List<BufferedImage> images = new ArrayList<BufferedImage>(length);
		
		for (int i = index; i < index + length; i++) {
			int sprite_x = i % swidth;
			int sprite_y = i / swidth;
			
			images.add(spritesheet.getSubimage(width*sprite_x, height*sprite_y, width, height));
		}
		
		return images;
	}
	
	List<BufferedImage> animation = null;
	Vector2f facing_dir = new Vector2f();
	
	/**
	 * Loads and Sets the current sprite to a given portion of the animation
	 */
	private float time, tempTime = 0;
	private boolean dashing = false;
	@Override
	public void updateWorld(float delta) {
		super.updateWorld(delta);
		time += 5*delta;
		if (animation != null) {
			if (dashing && facing_dir.x != 0) {
				int animation_frame = (facing_dir.x > 0) ? 36 : 45;
				animation = getAnimations(animation_frame, 2, 48, 48, 9, 14);
				
				time += 2*delta;
			} else if (facing_dir.x == -1) {
				animation = getAnimations(1, 3, 48, 48, 9, 14);
				animation.addAll(getAnimations(2, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(0, 1, 48, 48, 9, 14));
			} else if (facing_dir.x == 1) {
				animation = getAnimations(10, 3, 48, 48, 9, 14);
				animation.addAll(getAnimations(11, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(9, 1, 48, 48, 9, 14));
			} else if (facing_dir.y == -1) {
				animation = getAnimations(19, 1, 48, 48, 9, 14);
				animation.addAll(getAnimations(18, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(20, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(18, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(9, 1, 48, 48, 9, 14));
			} else if (facing_dir.y == 1) {
				animation = getAnimations(28, 1, 48, 48, 9, 14);
				animation.addAll(getAnimations(27, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(29, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(27, 1, 48, 48, 9, 14));
				animation.addAll(getAnimations(30, 1, 48, 48, 9, 14));
			}
			
			if (dashing && facing_dir.x != 0) {
				getTransform().setPosition(getTransform().getPosition().add(getTransform().getVelocity().mul(2*delta)));
				sprite = animation.get((int)time % animation.size());
				tempTime++;
				
				if (tempTime > 30) {
					dashing = false;
					tempTime = 0;
				}
			} else if (getTransform().getVelocity().x != 0 || getTransform().getVelocity().y != 0 ) {
				sprite = animation.get((int)time % (animation.size()-1));
			} else {
				sprite = animation.get(animation.size()-1);
			}
		}
		
		// Repositioning
		getTransform().setPosition(getTransform().getPosition().add(getTransform().getVelocity().mul(2*delta)));
	}
	
	/**
	 * Gets user input regarding the WASD to move
	 * and SPACE bar to activate the dash ability
	 */
	@Override
	public void processInput(float delta) {
		super.processInput(delta);
		
		Vector2f velocity = new Vector2f(0, 0);
		
		if (InputManager.getInputManager().keyDown(KeyEvent.VK_A)) {
			velocity.x -= 50;
			facing_dir = new Vector2f(-1, 0);
		} else if (InputManager.getInputManager().keyDown(KeyEvent.VK_S)) {
			velocity.y += 50;
			facing_dir = new Vector2f(0, -1);
		} else if (InputManager.getInputManager().keyDown(KeyEvent.VK_D)) {
			velocity.x += 50;
			facing_dir = new Vector2f(1, 0);
		} else if (InputManager.getInputManager().keyDown(KeyEvent.VK_W)) {
			velocity.y -= 50;
			facing_dir = new Vector2f(0, 1);
		}
		
		if (InputManager.getInputManager().keyDownOnce(KeyEvent.VK_SPACE) && facing_dir.x != 0) {
			dashing = true;
		}
		
		getTransform().setVelocity(velocity);
	}
}
