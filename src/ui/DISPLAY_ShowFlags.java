package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class DISPLAY_ShowFlags extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{}};

	protected static final String cmdName
		= "ShowFlags";
	protected static final String helpMessage
		= "Tells the interface to display any flag.";
	protected static final String usageMessage
		= "1. showflags";

	public DISPLAY_ShowFlags(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, MazeException	{

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}
		
		maze.showAllFlags();
	}
}
