package ui;

import java.io.PrintStream;


/** Help displays descriptions of all known commands. */
public class INFO_Help implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified context and output
	 *  strem. */
	public INFO_Help(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "help - gives a description of all commands.\n";
	}

	public String usage()	{
		return "help\n";
	}

	public void run(String[] args)	
		throws UIException	{

		ui.getOutStream().print("Help");
	}
}
