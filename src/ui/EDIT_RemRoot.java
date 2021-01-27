package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_RemRoot extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{}};

	protected static final String cmdName
		= "RemRoot";
	protected static final String helpMessage
		= "Removes the maze root.";
	protected static final String usageMessage
		= "remroot";

	public EDIT_RemRoot(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, MazeException	{

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}

		maze.remRoot();
		ui.getPi().clear();
		ui.getPrevious().clear();
	}
}
