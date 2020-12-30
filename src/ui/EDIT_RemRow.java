package ui;

import graph.*;

public class EDIT_RemRow implements CommandInterface	{

	private Maze maze;
	
	public EDIT_RemRow(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)
		throws UIException	{

		if(args.length == 1 || args.length > 2)	{ throw new IncorrectUsageException(2, args.length); }

		int pos = 0;

		try	{
			pos = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[1], 1);
		}

		try	{
			maze.remRow(pos);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(0, pos);
		}
	}

	public String description()	{
		return "remrow - Removes a row to the maze.\n";
	}

	public String usage()	{
		return	"remrow <pos>\n";
	}
}
