package ui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import maze.InterfaceableMaze;
import maze.MazeException;


/** OpenMaze reads a maze from an input stream, typically a file. */
public class IO_OpenMaze implements CommandInterface	{

	private InterfaceableMaze maze;

	/** Constructs the command with specified maze. */
	public IO_OpenMaze(InterfaceableMaze maze)	{
		this.maze = maze;
	}

	public String description()	{
		return "open - Reads a maze from a given file.\n";
	}

	public String usage()	{
		return "open <maze filename>\n";
	}


	/** @throws IOException if an I/O error occured.
	 *  @throws MazeException if the file can not be parsed. */
	public void run(String[] args)	
		throws UIException, MazeException	{
		if(args.length != 2)	{ throw new IncorrectUsageException(args.length, 2); }

		FileInputStream file = null;
		BufferedInputStream bs = null;
		try	{
			file = new FileInputStream(args[1]);
			bs = new BufferedInputStream(file);
			maze.read(bs);
			bs.close();
			file.close();
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		}
	}
}
