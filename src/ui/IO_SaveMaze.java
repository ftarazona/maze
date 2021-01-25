package ui;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import maze.InterfaceableMaze;
import maze.MazeException;


/** SaveMaze writes maze in an output stream. */
public class IO_SaveMaze implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public IO_SaveMaze(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "save - Writes the current maze in given file.\n";
	}

	public String usage()	{
		return "save <filename>\n";
	}


	/** @throws IOException if an I/O error occured. */
	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(args.length, 2); }

		FileOutputStream file = null;
		BufferedOutputStream bs = null;
		try	{
			file = new FileOutputStream(args[1]);
			bs = new BufferedOutputStream(file);
			maze.write(bs);
			bs.close();
			file.close();
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		}
	}
}
