package ui;

import maze.*;


/** NewMaze creates a new maze. */
public class IO_NewMaze extends Command implements CommandInterface	{

	protected static final int[][] expected	= {{Parser.INTEGER, Parser.INTEGER}};

	protected static final String cmdName
		= "NewMaze";
	protected static final String helpMessage
		= "Opens a new maze with given dimensions.";
	protected static final String usageMessage
		= "newmaze <width> <height>";

	public IO_NewMaze(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws MazeException	{
		int x = ((Integer)arg(0)).intValue();
		int y = ((Integer)arg(1)).intValue();

		ui.addMaze(x, y);
	}
}
