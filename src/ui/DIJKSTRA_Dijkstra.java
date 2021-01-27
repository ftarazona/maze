package ui;

import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class DIJKSTRA_Dijkstra extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{}};

	protected static final String cmdName
		= "Dijkstra";
	protected static final String helpMessage
		= "Runs Dijkstra Algorithm.";
	protected static final String usageMessage
		= "dijkstra";

	public DIJKSTRA_Dijkstra(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, NoRootException, MazeException	{

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}

		Vertex root = maze.getRoot();
		if(root == null)	{
			throw new NoRootException();
		}

		Dijkstra.dijkstra(maze, root, new Visited(), ui.getPi(), ui.getPrevious());
	}
}
