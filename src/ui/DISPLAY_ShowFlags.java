package ui;

import graph.InterfaceableMaze;
import graph.Maze;
import graph.MazeException;

public class DISPLAY_ShowFlags implements CommandInterface	{

	private InterfaceableMaze maze;

	public DISPLAY_ShowFlags(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 1)	{ throw new IncorrectUsageException(1, args.length); }

		maze.showAllFlags();
	}

	public String description()	{
		return "showflags - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "showflags\n";
	}
}
