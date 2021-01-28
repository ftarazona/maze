package ui;

import java.io.PrintStream;

import maze.InterfaceableMaze;
import maze.MazeException;


/** DisplayMaze displays the maze in an output stream. */
public class DISPLAY_DisplayMaze implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze and out stream.
	  */
	public DISPLAY_DisplayMaze(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "display - Displays the maze on the selected output.\n";
	}

	public String usage()	{
		return "display\n";
	}


	public void run(String[] args)	
		throws UIException, MazeException	{

		ui.getMaze().display(ui.getOutStream());
	}
}
