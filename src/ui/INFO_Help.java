package ui;

import java.io.PrintStream;


/** Help displays descriptions of all known commands. */
public class INFO_Help implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified context and output
	 *  strem. */
	public INFO_Help(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 	
		"Help		~ Gives a description of all commands.";
	}

	public String usage()	{
		return "help";
	}

	public void run(String[] args)	
		throws UIException	{

		ui.println(	"~~~~~~~~\n" +
				"~ Help ~\n" +
				"~~~~~~~~\n");

		for(CommandInterface cmd: ui.getCommandList())	{
			ui.println(cmd.description());
		}

		ui.println("");
	}
}
