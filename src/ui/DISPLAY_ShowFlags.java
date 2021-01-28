package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** ShowFlags requires maze to show all flags when displaying. */
public class DISPLAY_ShowFlags implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_ShowFlags(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"ShowFlags 	~ Displays the flagged boxes of the maze.";
	}

	public String usage()	{
		return "showflags";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().showAllFlags();
	}
}
