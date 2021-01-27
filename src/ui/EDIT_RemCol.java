package ui;

import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_RemCol extends Command implements CommandInterface	{

	protected static final int[][] expected	= {{Parser.INTEGER}};

	protected static final String cmdName
		= "RemCol";
	protected static final String helpMessage
		= "Removes a column to the maze at given position.";
	protected static final String usageMessage
		= "remcol <pos>";

	public EDIT_RemCol(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()
		throws NoMazeException, MazeException	{
		int pos = ((Integer)arg(0)).intValue();

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}

		maze.remCol(pos);
	}
}
