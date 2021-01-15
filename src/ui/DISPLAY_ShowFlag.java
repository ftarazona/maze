package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


public class DISPLAY_ShowFlag implements CommandInterface	{

	private InterfaceableMaze maze;

	public DISPLAY_ShowFlag(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "show - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "show <flag>\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(2, args.length); }

		maze.show(UIContext.flag(args[1]));
	}
}
