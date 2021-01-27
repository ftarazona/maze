package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_SetRoot extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{Parser.INTEGER, Parser.INTEGER}};

	protected static final String cmdName
		= "SetRoot";
	protected static final String helpMessage
		= "Sets the maze root at given coordinates.";
	protected static final String usageMessage
		= "setroot <x> <y>";

	public EDIT_SetRoot(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, MazeException	{

		int x = ((Integer)arg(0)).intValue();
		int y = ((Integer)arg(1)).intValue();
		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}

		maze.setRoot(x, y);
		ui.getPi().clear();
		ui.getPrevious().clear();
	}
}
