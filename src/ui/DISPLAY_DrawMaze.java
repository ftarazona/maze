package ui;

import java.io.IOException;

import maze.InterfaceableMaze;

import graphics.TileMap;


public class DISPLAY_DrawMaze implements CommandInterface	{

	private InterfaceableMaze maze;

	public DISPLAY_DrawMaze(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "draw - Draws the maze in a frame.\n";
	}

	public String usage()	{
		return "draw\n";
	}


	public void run(String[] args)
		throws UIException	{

		if(args.length != 1)	{
			throw new IncorrectUsageException(args.length, 1);
		}

		try	{
			TileMap.drawMaze(maze);
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		}
	}
}
