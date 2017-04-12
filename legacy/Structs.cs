namespace ChessSwap
{
    /// <summary>
    /// This struct is used to give the Span{(0, 1), (1, 0)} with statics limited to the
    /// interval of (a,b), where a,b are integer elements of the domain [(-1,-1), (1,1)].
    /// 
    /// <list type="Index2D">
    /// <item>
    /// <term>Author</term>
    /// <description>Nathan Skalka (nathansmail@cox.net)</description>
    /// </item>
    /// <item>
    /// <term>Copyright</term>
    /// <description>Nathan Skalka (c) 2015</description>
    /// </item>
    /// </list>
    /// 
    /// </summary>
    public struct Index2D
    {
        public int x;   // The x component
        public int y;   // The y component

        // The downward Index2D.
        public static readonly Index2D down = new Index2D(0, -1);

        // The leftward Index2D.
        public static readonly Index2D left = new Index2D(-1, 0);
        // The diagonally lower left Index2D.
        public static readonly Index2D leftDown = new Index2D(-1, -1);
        // The diagonally upper left Index2D.
        public static readonly Index2D leftUp = new Index2D(-1, 1);

        // The rightward Index2D.
        public static readonly Index2D right = new Index2D(1, 0);
        // The diagonally lower right Index2D.
        public static readonly Index2D rightDown = new Index2D(1, -1);
        // The diagonally upper right Index2D.
        public static readonly Index2D rightUp = new Index2D(1, 1);

        // The upward Index2D.
        public static readonly Index2D up = new Index2D(0, 1);

        // The zero (origin) Index2D.
        public static readonly Index2D zero = new Index2D(0, 0);

        // Only construct
        public Index2D(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        // Operator override (+)
        public static Index2D operator +(Index2D  p1, Index2D p2)
        {
            return new Index2D(p1.x + p2.x, p1.y + p2.y);
        }

        // Operator override (-)
        public static Index2D operator -(Index2D  p1, Index2D p2)
        {
            return new Index2D(p1.x - p2.x, p1.y - p2.y);
        }

        // Operator override (*)
        public static Index2D operator *(int value, Index2D p)
        {
            return new Index2D(value*p.x, value*p.y);
        }

        // Operator override (/)
        public static Index2D operator /(int value, Index2D p)
        {
            return new Index2D(p.x/value, p.y/value);
        }

        // Operator override (==)
        public static bool operator ==(Index2D  p1, Index2D p2)
        {
            return (p1.x == p2.x && p1.y == p2.y);
        }

        // Operator override (!=)
        public static bool operator !=(Index2D  p1, Index2D p2)
        {
            return (p1.x != p2.x || p1.y != p2.y);
        }

        // Method override
        public override bool Equals(object o)
        {
            if (o.GetType() == typeof(Index2D))
    	    {   return ((Index2D)o == this);    }
            else
    	    {   return false;    }
        }

        // Method override
        public override int GetHashCode()
        {
            return base.GetHashCode();
        }
    }
}