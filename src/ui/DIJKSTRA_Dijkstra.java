package ui;

import dijkstra.Dijkstra;
import dijkstra.Pi;
import dijkstra.Previous;
import dijkstra.Visited;

import maze.InterfaceableMaze;
import maze.Vertex;


public class DIJKSTRA_Dijkstra implements CommandInterface	{

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	public DIJKSTRA_Dijkstra(InterfaceableMaze maze, Pi pi, Previous prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
	}

	public String description()	{
		return "dijkstra - Processes Dijkstra algorithm from the current root.\n";
	}
	
	public String usage()	{
		return 	"disjktra\n" +
			"WARNING: the current maze must have a root set.\n";
	}


	public void run(String[] args)	
		throws UIException	{
		
		Vertex root = maze.getRoot();
		if(root == null)	{
			throw new NoRootException();
		}

		Dijkstra.dijkstra(maze, root, new Visited(), pi, prev);
	}
}
