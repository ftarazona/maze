package ui;

import java.io.PrintStream;
import graph.InterfaceableMaze;
import graph.Maze;
import graph.MazeException;

public class DISPLAY_DisplayMaze implements CommandInterface	{

	private InterfaceableMaze maze;
	private PrintStream out;

	public DISPLAY_DisplayMaze(InterfaceableMaze maze, PrintStream out)	{
		this.maze = maze;
		this.out = out;
	}

	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length > 1)	{ throw new IncorrectUsageException(1, args.length); }

		maze.display(out);
	}

	public String description()	{
		return "display - Displays the maze on the selected output.\n";
	}

	public String usage()	{
		return "display\n";
	}
}
