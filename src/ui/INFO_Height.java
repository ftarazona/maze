package ui;

import maze.*;

public class INFO_Height implements CommandInterface	{

	private final UserInterface ui;

	public String usage()	{
		return "height";
	}

	public String description()	{
		return "Tells the height of the current maze.";
	}

	public INFO_Height(UserInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	
		throws NoMazeOpenedException	{
		ui.getOutStream().print(String.format("Height of the maze : %d\n", ui.getMaze().getHeight()));
	}
}
