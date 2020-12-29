package ui;

import graph.Maze;

public class DISPLAY_DisplayMaze implements CommandInterface	{

	private Maze maze;

	public DISPLAY_DisplayMaze(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length > 1)	{ throw new IncorrectUsageException(1, args.length); }

		System.out.println("");
		maze.display();
		System.out.println("");
	}

	public String usage()	{
		return "display\n";
	}
}
