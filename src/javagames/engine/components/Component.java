package javagames.engine.components;

import javagames.engine.GameObject;
import javagames.engine.interfaces.IComponent;

/** The bass class for the IComponent interface. The 
 * 	interface is overrode with empty methods, so the
 * 	only necessary implementations of a specific Component
 * 	subclass is given. */
public class Component implements IComponent {
	protected GameObject go;
	
	public Component(GameObject go) {
		this.go = go;
	}
	
	@Override
	public void processInput(float delta) {
		
	}

	@Override
	public void updateWorld(float delta) {
		
	}
}
