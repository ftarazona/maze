package ui;

import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_AddFlag extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{Parser.INTEGER, Parser.INTEGER, Parser.INTEGER}};

	protected static final String cmdName
		= "AddFlag";
	protected static final String helpMessage
		= "Adds a flag to the box at given coordinates.";
	protected static final String usageMessage
		= "rembox <x> <y> <flag>";

	public EDIT_AddFlag(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, MazeException	{
		int x = ((Integer)arg(0)).intValue();
		int y = ((Integer)arg(1)).intValue();
		int flags = ((Integer)arg(2)).intValue();

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}

		maze.addFlag(x, y, BoxFlag.valueOf(flags));
	}
}
