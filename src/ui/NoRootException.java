package ui;

public class NoRootException extends UIException	{

	public NoRootException()	{}

	public String getMessage()	{ return "Unable to find any BOX_START flag in the maze."; }
}
