package ui;

import java.io.PrintStream;
import graph.Maze;

public class DISPLAY_DisplayMaze implements CommandInterface	{

	private Maze maze;
	private PrintStream out;

	public DISPLAY_DisplayMaze(Maze maze, PrintStream out)	{
		this.maze = maze;
		this.out = out;
	}

	public void run(String[] args)	
		throws UIException	{
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
