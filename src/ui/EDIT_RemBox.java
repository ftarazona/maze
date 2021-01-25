package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemBox removes a box in the maze. */
public class EDIT_RemBox implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public EDIT_RemBox(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "rembox - Removes a box from the maze.\n";
	}

	public String usage()	{
		return "rembox <x> <y>\n";
	}


	/** @throws InvalidArgumentsException if an integer can not be
	 *  read.
	 *  @throws MazeException if the passed coordinates can not be
	 *  reached. */
	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 3)	{ throw new IncorrectUsageException(args.length, 3); }

		int x = 0, y = 0;

		try	{
			x = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[1], 1);
		}
		try	{
			y = Integer.parseInt(args[2]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[2], 2);
		}

		maze.remBox(x, y);
	}
}
