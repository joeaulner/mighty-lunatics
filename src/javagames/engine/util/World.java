package javagames.engine.util;

import javagames.engine.model.Matrix3x3f;

public class World {
	private static Matrix3x3f worldToScreenMatrix;

	/**
	 * Return the world matrix
	 * @return the world matrix
	 */
	public static Matrix3x3f getWorldToScreenMatrix() {
		return worldToScreenMatrix;
	}

	/**
	 * Set the world matrix
	 * @param matrix - the world matrix
	 */
	public static void setWorldToScreenMatrix(Matrix3x3f matrix) {
		worldToScreenMatrix = matrix;
	}
}
