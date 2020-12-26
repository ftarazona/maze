package graph;

/*
 * The class below implements the Vertex interface.
 *
 * Although the course states it might be an abstract class extended
 *  by different kind of boxes, I think there are not enough 
 *  significant differences that may justify the need for an abstract
 *  class.
 * Therefore, the class adds an attribute describing the category of
 *  the box to allow the graph to estimate the distances.
 */

public abstract class Box
	implements Vertex	{

	/* defaultID indicates there is no Box.
	 * It can prove useful when making recursive functions for
	 *  instance.
	 */

	private static int nextID = 0;

	public static final char BOX_WALL	= 'W';
	public static final char BOX_EMPTY	= 'E';
	public static final char BOX_STAIRS	= 'S';
	public static final char BOX_WATER	= 'O';
	public static final char BOX_BRIDGE	= 'B';

	public static final int NO_FLAG		= 0;
	public static final int ON_PATH 	= 1;
	public static final int TO_WRITE 	= 2;
	public static final int TO_DRAW 	= 4;
	public static final int START		= 8;
	public static final int END		= 16;

	/* A box is unique by its coordinates. Therefore we need three
	 *  numbers to give an ID :
	 * * the two coordinates.
	 * * the maximum of the first one.
	 * Then the ID is x * maxx + y and a euclidian division allows
	 *  to find back x and y. Moreover, if maxx is minimal, the
	 *  IDs given are successive, which allows making arrays in
	 *  which index and IDs can be identified, with an optimal
	 *  cost in memory. */

	private final int x;
	private final int y;
	private final int z;
	private final int id;
	private int flags;

	/* Default constructor
	 *  the ID by default is -1, which is maxx = 0, x = 0, y = -1.
	 *  the BoxType is EMPTY. */

	public Box(int x, int y, int z)	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = nextID;
		nextID++;
		this.flags = NO_FLAG;
	}

	public int getID()	{
		return id;
	}

	public int getX()	{
		return x;
	}

	public int getY()	{
		return y;
	}

	public int getZ()	{
		return z;
	}

	public abstract int getPracticability();

	public int getFlags()	{
		return flags;
	}

	public boolean hasFlag(int flag)	{
		return (flags & flag) != 0;
	}

	public void setFlags(int flags)	{
		this.flags = flags;
	}

	public void addFlag(int flag)	{
		this.flags |= flag;
	}

	public void remFlag(int flag)	{
		this.flags &= ~flag;
	}

	public void clearFlags()	{
		this.flags = NO_FLAG;
	}
}
