package ui;

import java.util.*;
import dijkstra.*;
import maze.*;


/** NewMaze creates a new maze. */
public class DIJKSTRA_TracePath extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{Parser.INTEGER, Parser.INTEGER}, {}};

	protected static final String cmdName
		= "TracePath";
	protected static final String helpMessage
		= "Trace fatest path to position or to every end flags.";
	protected static final String usageMessage
		= "1. tracepath <x> <y>\n" +
		  "2. tracepath";

	public DIJKSTRA_TracePath(Object[] arguments, CoreInterface ui)	
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws NoMazeException, NoRootException, MazeException	{

		InterfaceableMaze maze = ui.getMaze();
		if(maze == null)	{
			throw new NoMazeException();
		}
		
		ArrayList<Vertex> toReach = new ArrayList<Vertex>();

		if(usage == 0)	{
			int x = ((Integer)arg(0)).intValue();
			int y = ((Integer)arg(1)).intValue();
			toReach.add(maze.getBox(x, y));
		} else if(usage == 1)	{
			toReach = maze.getSelection(BoxFlag.BOX_END);
		}

		for(Vertex v: toReach)	{
			maze.setSelection(ui.getPrevious().getFullPath(v), BoxFlag.BOX_MARKED);
		}
	}
}
