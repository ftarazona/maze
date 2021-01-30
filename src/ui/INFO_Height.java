package ui;

import maze.*;

/** Height tells the height of the maze. */
public class INFO_Height implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "height";
	}

	public String description()	{
		return 
		"Height		~ Tells the height of the current maze.";
	}

	public INFO_Height(PromptInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	
		throws NoMazeOpenedException	{
		ui.println(String.format("Height of the maze : %d", ui.getMaze().getHeight()));
	}
}
