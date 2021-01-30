package ui;

import maze.*;

public class INFO_Area implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "area";
	}

	public String description()	{
		return 
		"Area		~ Tells the area of the current maze.";
	}

	public INFO_Area(PromptInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	
		throws NoMazeOpenedException	{
		ui.getOutStream().print(String.format("Area of the maze : %d\n", ui.getMaze().getArea()));
	}
}
