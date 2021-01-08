package ui;

import dijkstra.*;
import graph.*;

public class DIJKSTRA_Dijkstra implements CommandInterface	{

	private Maze maze;
	private PiFunction pi;
	private PreviousFunction prev;

	public DIJKSTRA_Dijkstra(Maze maze, PiFunction pi, PreviousFunction prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public void run(String[] args)	
		throws UIException	{
		
		Vertex root = null;
		try	{
			root = maze.getSelection(BoxFlag.BOX_START).get(0);
		} catch (IndexOutOfBoundsException e)	{
			throw new NoRootException();
		}

		Dijkstra.dijkstra(maze, root, new Visited(), pi, prev);
	}

	public String description()	{
		return "dijkstra - Processes Dijkstra algorithm from the current root.\n";
	}
	
	public String usage()	{
		return 	"disjktra\n" +
			"WARNING: the current maze must have a root set.\n";
	}
}
