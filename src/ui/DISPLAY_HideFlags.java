package ui;

import graph.Maze;

public class DISPLAY_HideFlags implements CommandInterface	{

	private Maze maze;

	public DISPLAY_HideFlags(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length != 1)	{ throw new IncorrectUsageException(1, args.length); }

		maze.showAllFlags();
	}

	public String description()	{
		return "hideflags - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "hideflags\n";
	}
}
