package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** ShowFlags requires maze to show all flags when displaying. */
public class DISPLAY_ShowFlags implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public DISPLAY_ShowFlags(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "showflags - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "showflags\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 1)	{ throw new IncorrectUsageException(1, args.length); }

		maze.showAllFlags();
	}
}
