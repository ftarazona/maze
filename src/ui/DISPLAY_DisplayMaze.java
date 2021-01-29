package ui;

import java.io.PrintStream;

import maze.InterfaceableMaze;
import maze.MazeException;


/** DisplayMaze displays the maze in an output stream. */
public class DISPLAY_DisplayMaze implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze and out stream.
	  */
	public DISPLAY_DisplayMaze(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Display 	~ Displays the maze on the selected output.";
	}

	public String usage()	{
		return "display";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().display(ui.getOutStream());
		ui.println("");
	}
}
