package ui;

import maze.*;

public class INFO_Width implements CommandInterface	{

	private final UserInterface ui;

	public String usage()	{
		return "width";
	}

	public String description()	{
		return "Tells the width of the current maze.";
	}

	public INFO_Width(UserInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	{
		ui.getOutStream().print(String.format("Width of the maze : %d\n", ui.getMaze().getWidth()));
	}
}
