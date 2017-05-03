package javagames.game.chessboard;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javagames.engine.SpriteObject;
import javagames.engine.util.Screen;
import javagames.game.managers.BoardManager;
import javagames.game.sound.Sound;
import javagames.game.structs.Index2D;

public class Board extends SpriteObject {
//    private boolean showTiles = true;
    private Tile[] board;

    public Board() {
    	board = new Tile[64];
    	int x_offset = 310;
    	int y_offset = 200;
    	for (int x = 0; x < 8; x++) {
    		for (int y = 0; y < 8; y++) {
    			board[8*x + y] = new Tile(x*(Screen.width - 2*x_offset)/8 + 1.1f*x_offset, y*(Screen.height - y_offset)/8 + y_offset, x + 1, y + 1);
    		}
    	}
    	
    	Sound sound = new Sound("Pippin the Hunchback.wav");
    	sound.playSound(true);
    }
    
    @Override
    public void updateWorld(float delta) {
    	super.updateWorld(delta);
    	
    	for (Tile tile : board) {
    		tile.updateWorld(delta);
    	}
    }
    
    @Override
    public void render(Graphics g) {
    	super.render(g);
    	List<Chessman> pieces = new LinkedList<Chessman>();
    	
    	for (Tile tile : board) {
    		tile.render(g);
    		pieces.add(tile.getPiece());
    	}
    	
    	for (Chessman piece : pieces) {
    		piece.render(g);
    	}
    }
    
    public void swapTiles(Tile initTile, Tile finalTile) {
        Chessman piece = finalTile.getPiece();
        finalTile.setPiece(initTile.getPiece());
        initTile.setPiece(piece);
    }

    public void populateBoard() {
        Chessman previous = null;
        for (Tile tile : board) {
            BoardManager.getInstance().createPieceForTile(tile, previous);
            previous = tile.getPiece();
        }
    }

    public Tile getTile(int x, int y) {
        return getTile(new Index2D(x, y));
    }

    public Tile getTile(Index2D index) {
        for (Tile tile : board) {
            Index2D tileIndex = tile.getIndex();
            if (index.x == tileIndex.x && index.y == tileIndex.y) {
                return tile;
            }
        }

        return null;
    }
}
