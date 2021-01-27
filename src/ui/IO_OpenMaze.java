package ui;

import maze.*;
import java.util.*;
import java.io.*;

/** NewMaze creates a new maze. */
public class IO_OpenMaze extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{Parser.STRING}};

	protected static final String cmdName
		= "OpenMaze";
	protected static final String helpMessage
		= "Reads a new maze from a file.";
	protected static final String usageMessage
		= "openmaze <filename>";

	public IO_OpenMaze(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()	
		throws MazeException, IOException	{
		String filename = (String)arg(0);

		ui.addMaze(filename);
	}
}
