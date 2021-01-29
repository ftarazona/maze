package ui;

import java.io.PrintStream;


/** Usage displays how to use a command. */
public class INFO_Usage implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified context and output
	 *  stream. */
	public INFO_Usage(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Usage		~ Gives the usage of a command.";
	}

	public String usage()	{
		return "usage <command>";
	}

	/** @throws UnknownCommandException if the passed command is 
	 *  not known within context. */
	public void run(String[] args)	
		throws UIException	{
	
		try	{
			ui.println("");
			ui.println(ui.fetchCommand(args[1]).usage());
			ui.println("");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
	}
}
