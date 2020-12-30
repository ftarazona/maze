package ui;

import graph.Maze;

public class DISPLAY_DisplayAll implements CommandInterface	{

	private Maze maze;

	public DISPLAY_DisplayAll(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length > 1)	{ throw new IncorrectUsageException(1, args.length); }

		System.out.println("");
		maze.displayAll();
		System.out.println("");
	}

	public String description()	{
		return "displayall - Displays the maze on the selected output, flagged and unflagged boxes.\n";
	}

	public String usage()	{
		return "displayall\n";
	}
}
