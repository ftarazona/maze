package ui;

import graph.InterfaceableMaze;
import graph.Maze;
import graph.MazeException;

public class DISPLAY_HideFlag implements CommandInterface	{

	private InterfaceableMaze maze;

	public DISPLAY_HideFlag(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(2, args.length); }

		maze.hide(UIContext.flag(args[1]));
	}

	public String description()	{
		return "hide - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "hide <flag>\n";
	}
}
