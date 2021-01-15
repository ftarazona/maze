package ui;

import graph.*;
import dijkstra.*;

public class EDIT_SetRoot implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	public EDIT_SetRoot(InterfaceableMaze maze, Pi pi, Previous prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

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

	public String description()	{
		return "setroot - Sets the position of the root\n";
	}

	public String usage()	{
		return	"setroot <x> <y>\n";
	}
}
