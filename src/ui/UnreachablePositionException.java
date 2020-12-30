package ui;

public class UnreachablePositionException extends UIException	{

	public static final long serialVersionUID = 202012301428L;
	private final int x;
	private final int y;

	public UnreachablePositionException(int x, int y)	{
		this.x = x;
		this.y = y;
	}

	public String getMessage()	{ return "Position " + String.valueOf(x) + " , " + String.valueOf(y) + " could not be reached."; }
}

