package javagames.engine.interfaces;

/** Used by the any GameObject class in order to 
 * 	pass off processing and updating to components
 * 	of a larger GameObject */
public interface IComponent {
	public void processInput(float delta);
	
	public void updateWorld(float delta);
}
