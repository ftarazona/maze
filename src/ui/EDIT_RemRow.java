package ui;

import graph.*;

public class EDIT_RemRow implements CommandInterface	{

	private InterfaceableMaze maze;
	
	public EDIT_RemRow(InterfaceableMaze maze)	{
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

		maze.remRow(pos);
	}

	public String description()	{
		return "remrow - Removes a row to the maze.\n";
	}

	public String usage()	{
		return	"remrow <pos>\n";
	}
}
