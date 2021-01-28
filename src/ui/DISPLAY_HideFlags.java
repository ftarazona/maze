package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** HideFlags requires maze to hide all flags when displaying. */
public class DISPLAY_HideFlags implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_HideFlags(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"HideFlags 	~ Displays the flagged boxes of the maze.";
	}

	public String usage()	{
		return "hideflags";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().hideAllFlags();
	}
}
