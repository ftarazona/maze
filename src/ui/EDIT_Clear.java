package ui;

import maze.*;
import dijkstra.*;

public class EDIT_Clear implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	public EDIT_Clear(InterfaceableMaze maze, Pi pi, Previous prev)	{ 
		this.maze 	= maze;
		this.pi		= pi;
		this.prev	= prev;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length != 1)	{
			throw new IncorrectUsageException(args.length, 1);
		}
		
		maze.clear();
	}

	public String description()	{
		return "clear - Removes all flags\n";
	}

	public String usage()	{
		return	"clear\n";
	}
}
