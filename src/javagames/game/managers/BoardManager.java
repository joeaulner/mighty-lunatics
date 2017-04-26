package javagames.game.managers;

import javagames.engine.model.Vector2f;
import javagames.game.chessboard.Board;
import javagames.game.chessboard.Chessman;
import javagames.game.chessboard.ChessmanBuilder;
import javagames.game.chessboard.Tile;
import javagames.game.structs.Index2D;
import javagames.game.util.Notification;
import javagames.game.util.NotificationDelegate;
import javagames.game.util.Notifier;

public class BoardManager implements NotificationDelegate {
    private Board board;
    private Tile selectedTile;

    private static BoardManager instance;

    public static BoardManager getInstance(Board board) {
        if (instance == null) {
        	instance = new BoardManager(board);
        }
        
        return instance;
    }

    public BoardManager(Board board) {
        this.board = board;
        Notifier.registerObserver(Notification.Tile_Is_Empty, this);
        Notifier.registerObserver(Notification.Tile_Is_Selected, this);
        Notifier.registerObserver(Notification.Tile_Stopped_Chessman, this);
        
        if (board != null) {
        	board.populateBoard();
        }
    }
    
	@Override
	public void notificationMethod(Notification notification, Object sender) {
		switch (notification) {
			case Tile_Is_Empty: fillEmptyTile(sender); break;
			case Tile_Is_Selected: selectTile(sender); break;
			case Tile_Stopped_Chessman: checkTiles(sender); break;
				
			default: break;
		}
	}

    public void createPieceForTile(Tile tile, Chessman previous) {
        Chessman piece = ChessmanBuilder.createNormalPiece();
        if (previous != null) {
            while (piece.getName().equals(previous.getName())) {
                piece = ChessmanBuilder.createNormalPiece();
            }
        }

        float x = tile.getTransform().getPosition().x;
        float y = tile.getTransform().getPosition().y;

        Vector2f position = new Vector2f(x, y);
        piece.getTransform().setPosition(position);
        tile.setPiece(piece);
    }

    public void checkTiles(Object sender) {
        Tile tile = (Tile)sender;
        String name = tile.getPiece().getName();

        for (int i = 0; i < 2; i++)
        {
            int matchCombo = 0;
            Tile[] tiles = new Tile[8];

            for (int j = 1; j <= 8; j++)
            {
                Tile tempTile;

                if (i == 0)
                {   tempTile = this.board.getTile(tile.getIndex().x, j);    }
                else
                {   tempTile = this.board.getTile(j, tile.getIndex().y);    }

                if (tempTile != null)
                {
                    if (tempTile.getPiece() != null)
                    {
                        if (name.equals(tempTile.getPiece().getName()))
                        {
                            tiles[matchCombo] = tempTile;
                            matchCombo += 1;
                        }
                        else if (matchCombo >= 3)
                        {   break;  }
                        else
                        {   matchCombo = 0; }
                    } else
                    {   matchCombo = 0; }
                }
            }

            if (matchCombo > 2)
            {
                for (int j = 0; j < matchCombo; j++)
                {   tiles[j].clear();   }
            }
        }
    }

    private void fillEmptyTile(Object sender) {
        Tile tile = (Tile)sender;
        refillColumn(tile.getIndex().x, tile.getIndex().y);
    }

    private boolean isValidMove(Tile tile, Index2D newIndex) {
        Index2D[] indices = tile.getPiece().moveablePositions();
        for (Index2D index : indices) {
            if (Index2D.equals(newIndex, Index2D.add(tile.getIndex(), index))) {
                return true;
            }
        }
        return false;
    }

    private void refillColumn(int column, int startY) {
        Tile lowerTile = board.getTile(column, startY);
        for (int j = startY; j <= 7; j++) {
            Tile higherTile = board.getTile(column, j + 1);
            board.swapTiles(lowerTile, higherTile);
            lowerTile = higherTile;
        }
        
        createPieceForTile(board.getTile(column, 8), null);
    }

    private void selectTile(Object sender) {
        Tile tile = (Tile) sender;
        if (selectedTile == null) {
            selectedTile = tile;
        } else if (selectedTile != tile && isValidMove(selectedTile, tile.getIndex())) {
            board.swapTiles(selectedTile, tile);
            Notifier.notifyObservers(Notification.Player_Did_Move, this);
            selectedTile = null;
        } else {
            selectedTile = null;
        }
    }
}
