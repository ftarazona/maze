package ui;

import java.io.PrintStream;
import maze.MazeException;

public class INFO_Usage implements CommandInterface	{

	private UIContext context;
	private PrintStream out;

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

	public void run(String[] args)	
		throws UIException, MazeException	{

		if(args.length != 2)	{
			throw new IncorrectUsageException(2, args.length);
		}

		out.print(context.usage(args[1]));
	}
}
