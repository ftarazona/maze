package ui;

/** Signals no root was found for running Dijkstra algorithm. */
public class NoRootException extends UIException	{

	public static final long serialVersionUID = 202012301428L;
	public NoRootException()	{}

	public String getMessage()	{ return "Unable to find any BOX_START flag in the maze."; }
}
