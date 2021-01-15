package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


public class DISPLAY_HideFlag implements CommandInterface	{

	private InterfaceableMaze maze;

	public DISPLAY_HideFlag(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "hide - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "hide <flag>\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(2, args.length); }

		maze.hide(UIContext.flag(args[1]));
	}
}
