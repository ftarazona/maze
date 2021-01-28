package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** HideFlags requires maze to hide all flags when displaying. */
public class DISPLAY_HideFlags implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_HideFlags(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "hideflags - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "hideflags\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().hideAllFlags();
	}
}
