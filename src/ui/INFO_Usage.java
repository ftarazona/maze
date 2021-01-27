package ui;

import java.io.PrintStream;


/** Usage displays how to use a command. */
public class INFO_Usage implements CommandInterface	{

	private UIContext context;
	private PrintStream out;

	/** Constructs the command with specified context and output
	 *  stream. */
	public INFO_Usage(UIContext context, PrintStream out)	{
		this.context = context;
		this.out = out;
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

		if(args.length != 2)	{
			throw new IncorrectUsageException(2, args.length);
		}

		out.print(context.usage(args[1]));
	}
}
