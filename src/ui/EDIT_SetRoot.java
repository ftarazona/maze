package ui;

import graph.*;
import dijkstra.*;

public class EDIT_SetRoot implements CommandInterface	{

	private Maze maze;
	private PiFunction pi;
	private PreviousFunction prev;

	public EDIT_SetRoot(Maze maze, PiFunction pi, PreviousFunction prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public void run(String[] args)	
		throws UIException	{

		if(args.length != 3)	{
			throw new IncorrectUsageException(3, args.length);
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
		
		try	{
			if(maze.setRoot(x, y))	{
				System.out.println("WARNING: new root.");
				pi.clear();
				prev.clear();
				maze.clearAll();
				maze.setRoot(x, y);
			}
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(x, y);
		}
	}

	public String description()	{
		return "setroot - Sets the position of the root\n";
	}

	public String usage()	{
		return	"setroot <x> <y>\n";
	}
}
