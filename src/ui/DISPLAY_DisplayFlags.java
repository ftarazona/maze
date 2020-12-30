package ui;

import graph.Maze;

public class DISPLAY_DisplayFlags implements CommandInterface	{

	private Maze maze;

	public DISPLAY_DisplayFlags(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length != 1)	{ throw new IncorrectUsageException(1, args.length); }

		System.out.println("");
		maze.displayFlags();
		System.out.println("");
	}

	public String description()	{
		return "displayflags - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "displayflags\n";
	}
}
