package ui;

import maze.*;
import java.io.*;

/** NewMaze creates a new maze. */
public class IO_SaveMaze extends Command implements CommandInterface	{

	protected static final int[][]	expected = {{Parser.STRING}};

	protected static final String cmdName
		= "SaveMaze";
	protected static final String helpMessage
		= "Writes the current maze in a file.";
	protected static final String usageMessage
		= "savemaze <filename>";

	public IO_SaveMaze(Object[] arguments, CoreInterface ui)
		throws IncorrectUsageException	{
		super(arguments, expected, ui);
	}

	public void run()
		throws NoMazeException, MazeException, IOException	{

		String filename = (String)arg(0);
		FileOutputStream file = new FileOutputStream(filename);
		BufferedOutputStream buf = new BufferedOutputStream(file);
		InterfaceableMaze maze = ui.getMaze();

		if(maze == null)	{
			throw new NoMazeException();
		}

		maze.write(buf);
		buf.close();
		file.close();
	}
}
