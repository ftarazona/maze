package ui;

import graph.*;

public class EDIT_RemBox implements CommandInterface	{

	private Maze maze;

	public EDIT_RemBox(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length != 3)	{ throw new IncorrectUsageException(3, args.length); }

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

		try	{
			maze.remBox(x, y);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(x, y);
		}
	}

	public String description()	{
		return "rembox - Removes a box from the maze.\n";
	}

	public String usage()	{
		return "rembox <x> <y>\n";
	}
}
