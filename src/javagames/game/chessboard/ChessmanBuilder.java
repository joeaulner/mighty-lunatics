package javagames.game.chessboard;

import java.lang.reflect.Constructor;
import java.util.Random;

public class ChessmanBuilder {
	private static Chessman[] pieces;
	
	private static Chessman queen = new Queen();
	private static Chessman king = new King();
	
	public static Chessman createNormalPiece() {
        if (pieces != null) {
            Random random = new Random();
            return instantiate(pieces[random.nextInt(pieces.length-1)].getClass());
        }

        return null;
    }

	public static Chessman createKingPiece() {
		if (king != null) {
			return instantiate(king.getClass());
		}
		
		return null;
	}
	
	public static Chessman createQueenPiece() {
		if (queen != null) {
			return instantiate(queen.getClass());
		}
		
		return null;
	}
	
	private static Chessman instantiate(Class<? extends Chessman> classType) {
		try {
			Class<?> clazz = Class.forName(classType.getName());
			Constructor<?> ctor = clazz.getConstructor(String.class);
			Object object = ctor.newInstance();
			return (Chessman)object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
