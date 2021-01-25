package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.InterfaceableMaze;
import maze.MazeException;


/** SetRoot sets a new root in the maze. */
public class EDIT_SetRoot implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_SetRoot(InterfaceableMaze maze, Pi pi, Previous prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public String description()	{
		return "setroot - Sets the position of the root\n";
	}

	public String usage()	{
		return	"setroot <x> <y>\n";
	}


	/** @throws InvalidArgumentsException if position can not be
	 *  read. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length != 3)	{
			throw new IncorrectUsageException(args.length, 3);
		}

		int x = 0, y = 0;

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
		
		if(maze.setRoot(x, y))	{
			System.out.println("WARNING: new root.");
			pi.clear();
			prev.clear();
		}
	}
}
