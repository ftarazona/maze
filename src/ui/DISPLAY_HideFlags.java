package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class DISPLAY_HideFlags extends Command implements CommandInterface	{

	protected static final int[][] expected	= {{}};

	protected static final String cmdName
		= "HideFlags";
	protected static final String helpMessage
		= "Tells the interface not to display any flag.";
	protected static final String usageMessage
		= "1. hideflags";

	public DISPLAY_HideFlags(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, MazeException	{

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}
		
		maze.hideAllFlags();
	}
}
