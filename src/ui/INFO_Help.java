package ui;

import java.io.PrintStream;


/** Help displays descriptions of all known commands. */
public class INFO_Help implements CommandInterface	{

	private UIContext context;
	private PrintStream out;

	/** Constructs the command with specified context and output
	 *  strem. */
	public INFO_Help(UIContext context, PrintStream out)	{
		this.context = context;
		this.out = out;
	}

	public String description()	{
		return "help - gives a description of all commands.\n";
	}

	public String usage()	{
		return "help\n";
	}

	public void run(String[] args)	
		throws UIException	{

		if(args.length != 1)	{
			throw new IncorrectUsageException(1, args.length);
		}

		out.print(context.help());
	}
}
