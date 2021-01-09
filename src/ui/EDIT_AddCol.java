package ui;

import graph.*;

public class EDIT_AddCol implements CommandInterface	{
	
	private Maze maze;

	public EDIT_AddCol(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{

		if(args.length == 1 || args.length > 3)	{ throw new IncorrectUsageException(3, args.length); }

		int pos = 0, type = UIContext.boxType("null");

		try	{
			pos = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[1], 1);
		}

		try	{
			type = UIContext.boxType(args[2]);
		} catch (IndexOutOfBoundsException e)	{
			type = UIContext.boxType("null");
		} catch (UnknownBoxTypeException e)	{
			throw e;
		}

		try	{
			maze.addCol(pos, type);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(pos, 0);
		}
	}

	public String description()	{
		return "addcol - Adds a column to the maze.\n";
	}

	public String usage()	{
		return	"1. addcol <pos>\n" +
			"2. addcol <pos> <type>\n";
	}
}
