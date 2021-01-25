package ui;

import maze.InterfaceableMaze;
import maze.MazeContext;
import maze.MazeException;


/** NewMaze creates a new maze. */
public class IO_NewMaze implements CommandInterface	{
	
	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public IO_NewMaze(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "new - Creates a new maze with given dimensions.\n";
	}

	public String usage()	{
		return 	"1. new <>\n" +
			"2. new <height> <width>\n" +
			"3. new <height> <width> <boxtype>\n";
	}


	/** @throws InvalidArgumentsException if dimensions can not
	 *  be read.
	 *  @throws UnknownBoxTypeException if the passed box type
	 *  does not match any.
	 *  @throws MazeException if Maze can not create the new maze.
	 */
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
}
