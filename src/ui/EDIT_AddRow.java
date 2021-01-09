package ui;

import graph.*;

public class EDIT_AddRow implements CommandInterface	{
	
	private Maze maze;

	public EDIT_AddRow(Maze maze)	{
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
			maze.addRow(pos, type);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(0, pos);
		}
	}
	
	public String description()	{
		return "addrow - Adds a row to the maze.\n";
	}

	public String usage()	{
		return	"1. addrow <pos>\n" +
			"2. addrow <pos> <type>\n";
	}
}
