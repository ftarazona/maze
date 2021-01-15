package ui;

import graph.*;

public class EDIT_RemCol implements CommandInterface	{

	private Maze maze;
	
	public EDIT_RemCol(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)
		throws UIException, MazeException	{

		if(args.length != 2)	{ throw new IncorrectUsageException(args.length, 2); }

		int pos = 0;

		try	{
			pos = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[1], 1);
		}

		maze.remCol(pos);
	}

	public String description()	{
		return "remcol - Removes a column to the maze.\n";
	}

	public String usage()	{
		return	"remcol <pos>\n";
	}
}
