package javagames.game.structs;

public class Index2D
{
    public int x;   // The x component
    public int y;   // The y component

    // The downward Index2D.
    public static final Index2D down = new Index2D(0, -1);

    // The leftward Index2D.
    public static final Index2D left = new Index2D(-1, 0);
    // The diagonally lower left Index2D.
    public static final Index2D leftDown = new Index2D(-1, -1);
    // The diagonally upper left Index2D.
    public static final Index2D leftUp = new Index2D(-1, 1);

    // The rightward Index2D.
    public static final Index2D right = new Index2D(1, 0);
    // The diagonally lower right Index2D.
    public static final Index2D rightDown = new Index2D(1, -1);
    // The diagonally upper right Index2D.
    public static final Index2D rightUp = new Index2D(1, 1);

    // The upward Index2D.
    public static final Index2D up = new Index2D(0, 1);

    // The zero (origin) Index2D.
    public static final Index2D zero = new Index2D(0, 0);

    // Only construct
    public Index2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public static Index2D add(Index2D  p1, Index2D p2) {
        return new Index2D(p1.x + p2.x, p1.y + p2.y);
    }

    public static Index2D subtract(Index2D  p1, Index2D p2) {
        return new Index2D(p1.x - p2.x, p1.y - p2.y);
    }

    public static Index2D mul(int value, Index2D p) {
        return new Index2D(value*p.x, value*p.y);
    }

    public static Index2D div(int value, Index2D p) {
        return new Index2D(p.x/value, p.y/value);
    }

    public static boolean equals(Index2D  p1, Index2D p2) {
        return (p1.x == p2.x && p1.y == p2.y);
    }

    public static boolean notEquals(Index2D  p1, Index2D p2) {
        return (p1.x != p2.x || p1.y != p2.y);
    }

    // Method override
    public boolean equals(Object o) {
        if (o.getClass() == Index2D.class)
	    {   return ((Index2D)o == this);    }
        else
	    {   return false;    }
    }
}