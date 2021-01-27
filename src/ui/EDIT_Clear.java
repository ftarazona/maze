package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.InterfaceableMaze;


/** Clear clears all the flags in the maze. */
public class EDIT_Clear implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_Clear(InterfaceableMaze maze, Pi pi, Previous prev)	{ 
		this.maze 	= maze;
		this.pi		= pi;
		this.prev	= prev;
	}

	public String description()	{
		return "clear - Removes all flags\n";
	}

	public String usage()	{
		return	"clear\n";
	}


	public void run(String[] args)	
		throws UIException	{

		if(args.length != 1)	{
			throw new IncorrectUsageException(args.length, 1);
		}

		maze.clear();
		pi.clear();
		prev.clear();
	}
}
