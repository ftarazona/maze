package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** ShowFlag requires maze to show a flag when displaying. */
public class DISPLAY_ShowFlag implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public DISPLAY_ShowFlag(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "show - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "show <flag>\n";
	}


	/** @throws UnknownFlagException if the passed flag does not
	 *  match any. */
	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(2, args.length); }

		maze.show(UIContext.flag(args[1]));
	}
}
