package maze;

public class MazeOutOfBoundsException extends MazeException	{

	public static final long serialVersionUID = 202101151550L;
	private int x;
	private int y;
	private int xMax;
	private int yMax;

	public MazeOutOfBoundsException(int x, int y, int xMax, int yMax)	{
		this.x 		= x;
		this.y 		= y;
		this.xMax 	= xMax;
		this.yMax 	= yMax;
	}

	public int getX()	{ return x; }
	public int getY()	{ return y; }
	public int getXMax()	{ return xMax; }
	public int getYMax()	{ return yMax; }

	@Override
	public String getMessage()	{
		return String.format("Could not reach coordinates (%d, %d). Valid coordinates for this maze are between (0, 0) and (%d, %d).", x, y, xMax, yMax);
	}
}
