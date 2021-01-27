package ui;

import java.io.*;
import maze.*;
import dijkstra.*;

public abstract class Command implements CommandInterface	{

	protected final Object[]	arguments;
	protected final CoreInterface	ui;
	protected final int		usage;

	static protected final String	cmdName	= new String();
	static protected final String	helpMessage = new String();
	static protected final String	usageMessage = new String();

	public Command(Object[] arguments, int[][] expected, CoreInterface ui)
		throws IncorrectUsageException	{

		usage	= Parser.checkArguments(arguments, expected);
		if(usage == -1)	{
			throw new IncorrectUsageException();
		}
		this.ui		= ui;
		this.arguments 	= arguments;
	}

	public String help()	{ return helpMessage; }
	public String usage()	{ return usageMessage; }
	public String toString()	{
		String ret = "cmdName";
		for(Object obj : arguments)	{
			ret = ret.concat(" " + obj.toString());
		}
		return ret;
	}

	protected Object arg(int index)	{ return arguments[index]; }
	public abstract void run()
		throws UIException, MazeException, IOException;
}
