package ui;

import maze.InterfaceableMaze;
import maze.Maze;
import maze.MazeException;

public class DISPLAY_HideFlags implements CommandInterface	{

	private InterfaceableMaze maze;

	public DISPLAY_HideFlags(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 1)	{ throw new IncorrectUsageException(1, args.length); }

		maze.hideAllFlags();
	}

	public String description()	{
		return "hideflags - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "hideflags\n";
	}
}
