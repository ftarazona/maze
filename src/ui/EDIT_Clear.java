package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_Clear extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{}};

	protected static final String cmdName
		= "ClearFlags";
	protected static final String helpMessage
		= "Clears the maze flags.";
	protected static final String usageMessage
		= "clear";

	public EDIT_Clear(Object[] arguments, CoreInterface ui)	
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, MazeException	{

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}

		maze.clear();
		ui.getPi().clear();
		ui.getPrevious().clear();
	}
}
