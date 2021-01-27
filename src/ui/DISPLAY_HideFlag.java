package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** HideFlag requires the maze to hide a flag when displaying. */
public class DISPLAY_HideFlag implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public DISPLAY_HideFlag(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "hide - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "hide <flag>\n";
	}


	/** @throws UnknownFlagException if the passed flag does not
	 *  match any. */
	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(2, args.length); }

		maze.hide(UIContext.flag(args[1]));
	}
}
