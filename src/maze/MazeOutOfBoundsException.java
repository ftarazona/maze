package maze;

/** Signals an attempt to reach a box outside the maze. */
public class MazeOutOfBoundsException extends MazeException	{

	public static final long serialVersionUID = 202101151550L;
	private int x;
	private int y;
	private int xMax;
	private int yMax;

	/** Constructs a new MazeOutOfBoundsException with expected
	 *  and received coordinates.
	 *  @param x X coordinate attempted to access.
	 *  @param y Y coordinate attempted to access.
	 *  @param xMax maximum X coordinate able to access.
	 *  @param yMax maximum Y coordinate able to access. */
	public MazeOutOfBoundsException(int x, int y, int xMax, int yMax)	{
		this.x 		= x;
		this.y 		= y;
		this.xMax 	= xMax;
		this.yMax 	= yMax;
	}

	/** Returns the X coordinate attempted to access.
	 *  @return The X coordinate attempted to access. */
	public int getX()	{ return x; }

	/** Returns the Y coordinate attempted to access.
	 *  @return The Y coordinate attempted to access. */
	public int getY()	{ return y; }
	
	/** Returns the maximum X coordinate able to be accessed.
	 *  @return Rhe maximum X coordinate able to be accessed. */
	public int getXMax()	{ return xMax; }
	
	/** Returns the maximum Y coordinate able to be accessed.
	 *  @return Rhe maximum Y coordinate able to be accessed. */
	public int getYMax()	{ return yMax; }

	@Override
	public String getMessage()	{
		return String.format("Could not reach coordinates (%d, %d). Valid coordinates for this maze are between (0, 0) and (%d, %d).", x, y, xMax, yMax);
	}
}
