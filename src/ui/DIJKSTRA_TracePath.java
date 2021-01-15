package ui;

import java.util.ArrayList;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.Vertex;
import maze.MazeException;


public class DIJKSTRA_TracePath implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	public DIJKSTRA_TracePath(InterfaceableMaze maze, Pi pi, Previous prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public String description()	{
		return "tracepath - Traces minimal path to a given box in the maze.\n";
	}

	public String usage()	{
		return "tracepath <x> <y>\n";
	}

	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length != 3)	{ throw new IncorrectUsageException(args.length, 3); }

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
		
		path = prev.getFullPath(maze.getBox(x, y));
		maze.setSelection(path, BoxFlag.BOX_MARKED);
	}
}
