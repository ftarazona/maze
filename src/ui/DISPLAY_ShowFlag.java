package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class DISPLAY_ShowFlag extends Command implements CommandInterface	{

	protected static final int[][] expected	= {{Parser.INTEGER}};

	protected static final String cmdName
		= "ShowFlag";
	protected static final String helpMessage
		= "Tells the interface to display a specific flag.";
	protected static final String usageMessage
		= "1. showflag <flag>";

	public DISPLAY_ShowFlag(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, MazeException	{

		int flag = ((Integer)arg(0)).intValue();
		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}
		
		maze.show(BoxFlag.valueOf(flag));
	}
}
