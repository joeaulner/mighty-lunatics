package javagames.game.managers;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import javagames.engine.SpriteObject;
import javagames.engine.components.CollisionComponent;
import javagames.engine.model.Vector2f;
import javagames.game.chessboard.Board;
import javagames.game.chessboard.Chessman;
import javagames.game.chessboard.ChessmanBuilder;
import javagames.game.chessboard.Tile;
import javagames.game.structs.Index2D;
import javagames.game.util.Notification;
import javagames.game.util.NotificationDelegate;
import javagames.game.util.Notifier;

public class BoardManager extends SpriteObject implements NotificationDelegate {
    private Board board;
    private Tile selectedTile;

    private static BoardManager instance;

    public static BoardManager getInstance() {
        if (instance == null) {
        	instance = new BoardManager(new Board());
        	
        	if (instance.board != null) {
        		instance.board.populateBoard();
            }
        }
        
        return instance;
    }

    public BoardManager(Board board) {
        this.board = board;
        Notifier.registerObserver(Notification.Tile_Is_Empty, this);
        Notifier.registerObserver(Notification.Tile_Is_Selected, this);
        Notifier.registerObserver(Notification.Tile_Stopped_Chessman, this);
    }
    
    @Override
    public void updateWorld(float delta) {
    	super.updateWorld(delta);
    	this.board.updateWorld(delta);
    }
    
    @Override
    public void render(Graphics g) {
    	super.render(g);
    	this.board.render(g);
    }
    
    public void mouseClickAt(Vector2f position) {
    	System.out.print("Click at Position(" + position.x + ", " + position.y + "): ");
    	for (int x = 1; x <= 8; x++) {
    		for (int y = 1; y <= 8; y++) {
    			Tile tile = board.getTile(x, y);
    			CollisionComponent cc = tile.getCollisionComponent();
    			
    			if (cc != null) {
    				if (pointInPolygon( position, Arrays.asList(cc.getCollisionPoints()), false) ) {
    					System.out.println("Success");
    					tile.select(tile);
    					return;
    				}
    			}
    		}
    	}
    	
    	System.out.println("Failed");
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
        for (int j = startY; j > 1; j--) {
            Tile higherTile = board.getTile(column, j - 1);
            board.swapTiles(lowerTile, higherTile);
            lowerTile = higherTile;
        }
        
        createPieceForTile(board.getTile(column, 1), null);
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
    
    private boolean pointInPolygon(Vector2f point, List<Vector2f> poly, boolean winding) {
		int inside = 0;
		if (poly.size() > 2) {
			Vector2f start = poly.get(poly.size() - 1);
			boolean startAbove = start.y >= point.y;
			for (int i = 0; i < poly.size(); ++i) {
				Vector2f end = poly.get(i);
				boolean endAbove = end.y >= point.y;
				if (startAbove != endAbove) {
					float m = (end.y - start.y) / (end.x - start.x);
					float x = start.x + (point.y - start.y) / m;
					if (x >= point.x) {
						if (winding) {
							inside += startAbove ? 1 : -1;
						} else {
							inside = inside == 1 ? 0 : 1;
						}
					}
				}
				startAbove = endAbove;
				start = end;
			}
		}
		return inside != 0;
	}
}
