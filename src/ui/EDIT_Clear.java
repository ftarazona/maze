package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.InterfaceableMaze;


/** Clear clears all the flags in the maze. */
public class EDIT_Clear implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_Clear(PromptInterface ui)	{ 
		this.ui = ui;
	}

	public String description()	{
		return 
		"Clear 		~ Removes all flags";
	}

	public String usage()	{
		return	"clear";
	}


	public void run(String[] args)	
		throws UIException	{

		ui.getMaze().clear();
		ui.getPi().clear();
		ui.getPrevious().clear();
		ui.modify();
	}
}
