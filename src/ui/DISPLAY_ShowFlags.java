package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** ShowFlags requires maze to show all flags when displaying. */
public class DISPLAY_ShowFlags implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_ShowFlags(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "showflags - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "showflags\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().showAllFlags();
	}
}
