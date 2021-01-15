package ui;

import maze.InterfaceableMaze;
import maze.MazeContext;
import maze.MazeException;


public class EDIT_AddBox implements CommandInterface	{

	private InterfaceableMaze maze;

	public EDIT_AddBox(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "addbox - Adds or replaces a box of the maze.\n";
	}

	public String usage()	{
		return	"1. addbox <x> <y> <z> (empty)\n" +
			"2. addbox <x> <y> <z> <type>\n";
	}


	public void run(String[] args)
		throws UIException, MazeException	{
		
		if(args.length < MazeContext.MIN_ARGS)	{ 
			throw new IncorrectUsageException(args.length, MazeContext.MIN_ARGS); 
		}

		int boxType;
		int[] boxArgs = new int[args.length - 1];

		for(int i = 1; i <= 3; i++)	{
			try	{
				boxArgs[i - 1] = Integer.parseInt(args[i]);
			} catch (NumberFormatException e)	{
				throw new InvalidArgumentsException(args[i], i);
			}
		}

		try	{
			boxType = UIContext.boxType(args[4]);
		} catch (IndexOutOfBoundsException e)	{
			boxType = MazeContext.NULL_ID;
		}

		boxArgs[3] = 0;
		for(int i = 5; i < args.length; i++)	{
			try	{
				boxArgs[i - 1] = Integer.parseInt(args[i]);
			} catch (NumberFormatException e)	{
				throw new InvalidArgumentsException(args[i], i);
			}
		}

		maze.addBox(boxType, boxArgs);
	}
}
