package ui;

import java.util.ArrayList;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.Vertex;
import maze.MazeException;


/** TracePath command marks the path calculated by dijkstra to a
 *  given vertex */
public class DIJKSTRA_TracePath implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze, pi and
	 *  previous functions. */
	public DIJKSTRA_TracePath(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "tracepath - Traces minimal path to a given box in the maze.\n";
	}

	public String usage()	{
		return "tracepath" + 
			"tracepath <x> <y>\n";
	}

	/** @throws InvalidArgumentsException if x or y could not be
	 *  read. 
	 *  @throws MazeException if maze encountered an error. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		int x = 0, y = 0;
		ArrayList<Vertex> toReach = new ArrayList<Vertex>();

		try	{
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
			toReach.add(ui.getMaze().getBox(x, y));
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Coordinates are expected to be integers.");
		} catch (IndexOutOfBoundsException e)	{
			toReach = ui.getMaze().getSelection(BoxFlag.BOX_END);
		}
		
		for(Vertex v: toReach)	{
			ArrayList<Vertex> path = ui.getPrevious().getFullPath(v);
			ui.getMaze().setSelection(path, BoxFlag.BOX_MARKED);
			ui.modify();
		}
	}
}
