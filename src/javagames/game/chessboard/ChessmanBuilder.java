package javagames.game.chessboard;

import java.lang.reflect.Constructor;
import java.util.Random;

public class ChessmanBuilder {
	private static Chessman[] pieces = new Chessman[] {
			new Pawn("BlackPawn"),
			new Pawn("WhitePawn"),
			new Bishop("BlackBishop"),
			new Bishop("WhiteBishop"),
			new Knight("BlackKnight"),
			new Knight("WhiteKnight"),
			new Rook("BlackRook"),
			new Rook("WhiteRook")
	};
	
	private static Chessman queen = new Queen();
	private static Chessman king = new King();
	
	public static Chessman createNormalPiece() {
        if (pieces != null) {
            Random random = new Random();
            Chessman obj = pieces[random.nextInt(pieces.length-1)];
            System.out.println(obj.getName());
            return instantiate(obj);
        }

        return null;
    }

	public static Chessman createKingPiece() {
		if (king != null) {
			return instantiate(king);
		}
		
		return null;
	}
	
	public static Chessman createQueenPiece() {
		if (queen != null) {
			return instantiate(queen);
		}
		
		return null;
	}
	
	private static Chessman instantiate(Chessman piece) {
		try {
			Class<?> clazz = Class.forName(piece.getClass().getName());
			Constructor<?> ctor = clazz.getConstructor(String.class);
			Object object = ctor.newInstance(piece.getName());
			return (Chessman)object;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
