namespace ChessSwap
{
    public enum Color
    {
        White = 1,
        Black = -1
    };

    public enum Notification
    {
        Chessman_Deleted,

        Level_Ended,

        Player_Did_Move,
        Score_Did_Change,

        Tile_Is_Empty,
        Tile_Is_Selected,
        Tile_Stopped_Chessman
    };
}