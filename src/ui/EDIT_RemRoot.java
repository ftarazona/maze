package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemRoot removes the current root in maze. */ 
public class EDIT_RemRoot implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_RemRoot(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"RemRoot		~ Removes a potential root.";
	}

	public String usage()	{
		return	"remroot";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().remRoot();
		ui.getPi().clear();
		ui.getPrevious().clear();
		ui.modify();
	}
}
