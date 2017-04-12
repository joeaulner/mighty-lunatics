package javagames.engine;

import javagames.engine.interfaces.Drawable;
import javagames.engine.interfaces.InputListener;
import javagames.engine.model.Transform;

public interface GameObject extends Drawable, InputListener {
	public Transform getTransform();
}
