package javagames.game.chessboard;

import java.awt.Graphics;

import javagames.engine.SpriteObject;
import javagames.engine.util.Screen;
import javagames.game.managers.BoardManager;
import javagames.game.structs.Index2D;

public class Board extends SpriteObject {
//    private boolean showTiles = true;
    private Tile[] board;

    public Board() {
    	board = new Tile[64];
    	int x_offset = 100;
    	int y_offset = 100;
    	for (int x = 0; x < 8; x++) {
    		for (int y = 0; y < 8; y++) {
    			board[8*x + y] = new Tile(x*(Screen.width - x_offset)/8 + x_offset, y*(Screen.height - y_offset)/8 + y_offset, x + 1, y + 1);
    		}
    	}
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
    	
    	for (Tile tile : board) {
    		tile.render(g);
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
