package ui;

import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_RemBox extends Command implements CommandInterface	{

	protected static final int[][] expected = {{Parser.INTEGER, Parser.INTEGER}};

	protected static final String cmdName
		= "RemBox";
	protected static final String helpMessage
		= "Removes the box at given coordinates.";
	protected static final String usageMessage
		= "rembox <x> <y>";

	public EDIT_RemBox(Object[] arguments, CoreInterface ui)
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

		maze.remBox(x, y);
	}
}
