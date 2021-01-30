package ui;

import dijkstra.Dijkstra;
import dijkstra.Pi;
import dijkstra.Previous;
import dijkstra.Visited;

import maze.InterfaceableMaze;
import maze.Vertex;


/** Dijkstra command runs dijkstra algorithm on a given maze. */
public class DIJKSTRA_Dijkstra implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze, pi and
	 *  previous function. */
	public DIJKSTRA_Dijkstra(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Dijkstra	~ Processes Dijkstra algorithm from the current root.";
	}
	
	public String usage()	{
		return 	"dijkstra\n" +
			"WARNING: the current maze must have a root set.";
	}


	/** @throws NoRootException if the maze has no root. */
	public void run(String[] args)	
		throws UIException	{

		ui.getPi().clear();
		ui.getPrevious().clear();

		Vertex root = ui.getMaze().getRoot();
		if(root == null)	{
			throw new NoRootException();
		}

		Dijkstra.dijkstra(ui.getMaze(), root, new Visited(), ui.getPi(), ui.getPrevious());
	}
}
