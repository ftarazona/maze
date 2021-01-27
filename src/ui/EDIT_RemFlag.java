package ui;

import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_RemFlag extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{Parser.INTEGER, Parser.INTEGER, Parser.INTEGER}};

	protected static final String cmdName
		= "RemFlag";
	protected static final String helpMessage
		= "Removes a flag to the box at given coordinates.";
	protected static final String usageMessage
		= "rembox <x> <y> <flag>";

	public EDIT_RemFlag(Object[] arguments, CoreInterface ui)
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

		maze.remFlag(x, y, BoxFlag.valueOf(flags));
	}
}
