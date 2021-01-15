package ui;

import graph.*;

public class EDIT_AddCol implements CommandInterface	{
	
	private InterfaceableMaze maze;

	public EDIT_AddCol(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length == 1 || args.length > 3)	{ throw new IncorrectUsageException(args.length, 3); }

		int pos = 0, type;

		try	{
			pos = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[1], 1);
		}

		try	{
			type = UIContext.boxType(args[2]);
		} catch (IndexOutOfBoundsException e)	{
			type = MazeContext.NULL_ID;
		}

		maze.addCol(pos, type);
	}

	public String description()	{
		return "addcol - Adds a column to the maze.\n";
	}

	public String usage()	{
		return	"1. addcol <pos>\n" +
			"2. addcol <pos> <type>\n";
	}
}
