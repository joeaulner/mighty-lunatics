package javagames.game.chessboard;

import javagames.game.managers.BoardManager;
import javagames.game.structs.Index2D;

public class Board {
    private boolean showTiles = true;
    private Tile[] board;

    public void swapTiles(Tile initTile, Tile finalTile) {
        Chessman piece = finalTile.getPiece();
        finalTile.setPiece(initTile.getPiece());
        initTile.setPiece(piece);
    }

    public void populateBoard() {
        Chessman previous = null;
        for (Tile tile : board) {
            BoardManager.getInstance(null).createPieceForTile(tile, previous);
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
