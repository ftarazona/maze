package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class DISPLAY_DisplayMaze extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{}};

	protected static final String cmdName
		= "Display";
	protected static final String helpMessage
		= "Displays maze.";
	protected static final String usageMessage
		= "display";

	public DISPLAY_DisplayMaze(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, NoRootException, MazeException	{
		ui.displayMaze();
	}
}
