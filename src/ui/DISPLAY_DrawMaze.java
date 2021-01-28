package ui;

import java.io.IOException;

import maze.InterfaceableMaze;

import graphics.TileMap;


/** DrawMaze draws the maze in a tile map. */
public class DISPLAY_DrawMaze implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_DrawMaze(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Draw 		~ Draws the maze in a frame.";
	}

	public String usage()	{
		return "draw";
	}


	/** @throws IOException if an error occured while loading the
	 *  tile set. */
	public void run(String[] args)
		throws UIException	{

		try	{
			TileMap.drawMaze(ui.getMaze());
		} catch (Exception e)	{
			throw new UIException(e.getMessage());
		}
	}
}
