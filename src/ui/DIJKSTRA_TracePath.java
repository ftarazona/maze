package ui;

import dijkstra.*;
import graph.*;
import java.util.ArrayList;

public class DIJKSTRA_TracePath implements CommandInterface	{

	private Maze maze;
	private PiFunction pi;
	private PreviousFunction prev;

	public DIJKSTRA_TracePath(Maze maze, PiFunction pi, PreviousFunction prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public void run(String[] args)	
		throws UIException	{

		if(args.length != 3)	{ throw new IncorrectUsageException(3, args.length); }

		int x = 0, y = 0;
		ArrayList<Vertex> path = new ArrayList<Vertex>();

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
			path = prev.getFullPath(maze.getBox(x, y));
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(x, y);
		}

		maze.setSelection(path, BoxFlag.BOX_MARKED);
	}

	public String usage()	{
		return "tracepath <x> <y>\n";
	}

	public String description()	{
		return "tracepath - Traces minimal path to a given box in the maze.\n";
	}
}