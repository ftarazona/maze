package ui;

import java.io.IOException;

import maze.InterfaceableMaze;

import graphics.TileMap;


/** DrawMaze draws the maze in a tile map. */
public class DISPLAY_DrawMaze implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public DISPLAY_DrawMaze(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "draw - Draws the maze in a frame.\n";
	}

	public String usage()	{
		return "draw\n";
	}


	/** @throws IOException if an error occured while loading the
	 *  tile set. */
	public void run(String[] args)
		throws UIException	{

		if(args.length != 1)	{
			throw new IncorrectUsageException(args.length, 1);
		}

		try	{
			TileMap.drawMaze(maze);
		} catch (Exception e)	{
			throw new UIException(e.getMessage());
		}
	}
}
