package ui;

import maze.*;

public class INFO_Width implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "width";
	}

	public String description()	{
		return 
		"Width		~ Tells the width of the current maze.";
	}

	public INFO_Width(PromptInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	
		throws NoMazeOpenedException	{
		ui.getOutStream().print(String.format("Width of the maze : %d\n", ui.getMaze().getWidth()));
	}
}
