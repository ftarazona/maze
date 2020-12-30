package ui;

import graph.*;

public class EDIT_RemCol implements CommandInterface	{

	private Maze maze;
	
	public EDIT_RemCol(Maze maze)	{
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
			maze.remCol(pos);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(0, pos);
		}
	}

	public String description()	{
		return "remcol - Removes a column to the maze.\n";
	}

	public String usage()	{
		return	"remcol <pos>\n";
	}
}
