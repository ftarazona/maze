package ui;

import java.io.PrintStream;


/** Usage displays how to use a command. */
public class INFO_Usage implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified context and output
	 *  stream. */
	public INFO_Usage(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "usage - gives the usage of a command.\n";
	}

	public String usage()	{
		return "usage <command>\n";
	}

	/** @throws UnknownCommandException if the passed command is 
	 *  not known within context. */
	public void run(String[] args)	
		throws UIException	{

		ui.getOutStream().print("usage");
	}
}
