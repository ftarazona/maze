package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemRoot removes the current root in maze. */ 
public class EDIT_RemRoot implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_RemRoot(InterfaceableMaze maze, Pi pi, Previous prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public String description()	{
		return "remroot - Removes a potential root\n";
	}

	public String usage()	{
		return	"remroot\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length != 1)	{
			throw new IncorrectUsageException(args.length, 1);
		}
		
		maze.remRoot();
		pi.clear();
		prev.clear();
	}
}
