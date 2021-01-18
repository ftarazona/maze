package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.MazeException;


/** AddFlag adds a flag to a given box in maze. */
public class EDIT_AddFlag implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	/** Construcs the command with specified maze, pi and previous
	 *  functions. */
	public EDIT_AddFlag(InterfaceableMaze maze, Pi pi, Previous prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public String description()	{
		return "addflag - Adds or replaces a flag.\n";
	}

	public String usage()	{
		return	"addflag <x> <y> <flag>\n";
	}


	/** @throws InvalidArgumentsException if an integer value can
	 *  not be read.
	 *  @throws UnknownFlagException if the flag given does
	 *  not match any.
	 *  @throws MazeException if an error occured in Maze class,
	 *  such as trying to reach out of bounds coordinates. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length != 4)	{
			throw new IncorrectUsageException(4, args.length);
		}

		int x = 0, y = 0;
		BoxFlag flag = null;

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
		
		flag = UIContext.flag(args[3]);
		if(flag.equals(BoxFlag.BOX_START))	{
			pi.clear();
			prev.clear();
		}

		maze.addFlag(x, y, flag);
	}
}
