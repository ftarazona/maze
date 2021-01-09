package ui;

import graph.*;

public class EDIT_AddBox implements CommandInterface	{

	private Maze maze;

	public EDIT_AddBox(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)
		throws UIException	{
		
		if(args.length < MazeContext.MIN_ARGS)	{ 
			throw new IncorrectUsageException(MazeContext.MIN_ARGS, args.length); 
		}

		int boxID = MazeContext.NULL_ID;
		int[] boxArgs = new int[args.length - 1];

		for(int i = 1; i <= 3; i++)	{
		try	{
			boxArgs[i - 1] = Integer.parseInt(args[i]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[i-1], i);
		}
		}

		try	{
			boxID = UIContext.boxType(args[4]);
		} catch (IndexOutOfBoundsException e)	{
			boxID = MazeContext.NULL_ID;
		} catch (UnknownBoxTypeException e)	{
			throw e;
		}

		boxArgs[3] = 0;
		for(int i = 5; i < args.length; i++)	{
		try	{
			boxArgs[i - 1] = Integer.parseInt(args[i]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[i-1], i);
		}
		}


		try	{
			maze.addBox(boxID, boxArgs);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(boxArgs[0], boxArgs[1]);
		} catch (InvalidBoxArgumentsException e)	{
			throw new UIException("An internal error occured while adding a box.");
		}
	}

	public String description()	{
		return "addbox - Adds or replaces a box of the maze.\n";
	}

	public String usage()	{
		return	"1. addbox <x> <y> <z> (empty)\n" +
			"2. addbox <x> <y> <z> <type>\n";
	}
}
