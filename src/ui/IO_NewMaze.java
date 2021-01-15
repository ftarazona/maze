package ui;

import java.io.IOException;
import graph.*;

public class IO_NewMaze implements CommandInterface	{
	
	private Maze maze;

	public IO_NewMaze(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{
		
		if(args.length == 1 || args.length > 4)	{ throw new IncorrectUsageException(args.length, 4); }

		int usage = 3;
		int width = 0, height = 0, type;

		try	{
			height = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[1], 1);
		} catch (IndexOutOfBoundsException e)	{
			usage = 1;
		}

		try	{
			width = Integer.parseInt(args[2]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[2], 2);
		}
		try	{
			type = UIContext.boxType(args[3]);
		} catch (IndexOutOfBoundsException e)	{
			type = MazeContext.NULL_ID;
		}

		if(usage == 1)	{ maze.newMaze(0, 0, UIContext.boxType("null")); }
		else	{ maze.newMaze(height, width, type); }
	}

	public String description()	{
		return "new - Creates a new maze with given dimensions.\n";
	}

	public String usage()	{
		return 	"1. new <>\n" +
			"2. new <height> <width>\n" +
			"3. new <height> <width> <boxtype>\n";
	}
}
