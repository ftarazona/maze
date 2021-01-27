package ui;

import maze.*;


/** NewMaze creates a new maze. */
public class EDIT_AddBox extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{Parser.INTEGER, Parser.INTEGER, Parser.INTEGER, Parser.INTEGER}};

	protected static final String cmdName
		= "AddBox";
	protected static final String helpMessage
		= "Adds a box to the current maze.";
	protected static final String usageMessage
		= "addbox <type> <x> <y> <z> <flags> <info>";

	public EDIT_AddBox(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()
		throws NoMazeException, MazeException	{
		int[] args = new int[arguments.length];
		for(int i = 0; i < arguments.length; i++)	{
			args[i] = ((Integer)arg(i)).intValue();
		}

		InterfaceableMaze maze = ui.getMaze();
		
		if(maze == null)	{
			throw new NoMazeException();
		}
		
		maze.addBox(args);
	}
}
