package ui;

import graph.*;

public class EDIT_AddBox implements CommandInterface	{

	private Maze maze;
	private UIContext context;

	public EDIT_AddBox(Maze maze)	{
		this.maze = maze;
		context = new UIContext();
		context.setBoxTab();
	}

	public void run(String[] args)
		throws UIException	{
		
		if(args.length < 4 || args.length > 5)	{ throw new IncorrectUsageException(5, args.length); }

		int x = 0, y = 0, z = 0, type = context.boxType("null");

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
			z = Integer.parseInt(args[3]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[3], 3);
		}
		try	{
			type = context.boxType(args[4]);
		} catch (IndexOutOfBoundsException e)	{
			type = context.boxType("null");
		} catch (UnknownBoxTypeException e)	{
			throw e;
		}

		try	{
			maze.addBox(x, y, z, type);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(x, y);
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
