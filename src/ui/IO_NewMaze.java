package ui;

import graph.Maze;
import graph.BoxContext;

public class IO_NewMaze implements CommandInterface	{
	
	private Maze maze;
	private final UIContext context;
	private final BoxContext boxcontext;

	public IO_NewMaze(Maze maze)	{
		this.maze = maze;
		this.context = new UIContext();
		this.boxcontext = new BoxContext();
	}

	public void run(String[] args)	
		throws UIException	{
		
		if(args.length > 4)	{ throw new IncorrectUsageException(4, args.length); }

		int usage = 3;
		int width = 0, height = 0, type = BoxContext.NULL_ID;

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
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException(2, args.length);
		}

		try	{
			type = context.boxType(args[3]);
		} catch (IndexOutOfBoundsException e)	{
			usage = 2;
		} catch (UnknownBoxTypeException e)	{
			throw e;
		}

		if(usage == 1)	{ maze.newMaze(0, 0, boxcontext.NULL_ID); }
		else if(usage == 2)	{ maze.newMaze(height, width, boxcontext.NULL_ID); }
		else	{ maze.newMaze(height, width, type); }
	}

	public String usage()	{
		return 	"1. new <>\n" +
			"2. new <height> <width>\n" +
			"3. new <height> <width> <boxtype>\n";
	}
}
