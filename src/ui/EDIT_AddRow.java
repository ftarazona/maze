package ui;

import graph.*;

public class EDIT_AddRow implements CommandInterface	{
	
	private InterfaceableMaze maze;

	public EDIT_AddRow(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length < 2 || args.length > 3)	{ throw new IncorrectUsageException(args.length, 3); }

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

		maze.addRow(pos, type);
	}
	
	public String description()	{
		return "addrow - Adds a row to the maze.\n";
	}

	public String usage()	{
		return	"1. addrow <pos>\n" +
			"2. addrow <pos> <type>\n";
	}
}
