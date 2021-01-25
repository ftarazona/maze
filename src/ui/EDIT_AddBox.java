package ui;

import maze.InterfaceableMaze;
import maze.MazeContext;
import maze.MazeException;


/** AddBox adds a box in the maze */
public class EDIT_AddBox implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
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


	/** @throws InvalidArgumentsException if an integer value can
	 *  not be read.
	 *  @throws UnknownBoxTypeException if the box type given does
	 *  not match any.
	 *  @throws MazeException if an error occured in Maze class,
	 *  such as trying to reach out of bounds coordinates. */
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
