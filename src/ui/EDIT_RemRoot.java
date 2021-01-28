package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemRoot removes the current root in maze. */ 
public class EDIT_RemRoot implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_RemRoot(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "remroot - Removes a potential root\n";
	}

	public String usage()	{
		return	"remroot\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().remRoot();
		ui.getPi().clear();
		ui.getPrevious().clear();
		ui.modify();
	}
}
