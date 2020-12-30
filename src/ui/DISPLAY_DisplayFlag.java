package ui;

import graph.Maze;

public class DISPLAY_DisplayFlag implements CommandInterface	{

	private Maze maze;
	private UIContext context;

	public DISPLAY_DisplayFlag(Maze maze)	{
		this.maze = maze;
		context = new UIContext();
		context.setFlagTab();
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(2, args.length); }

		System.out.println("");
		maze.display(context.flag(args[1]));
		System.out.println("");
	}

	public String description()	{
		return "displayflag - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "displayflag <flag>\n";
	}
}
