package ui;

import graph.*;
import fileops.*;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class IO_OpenMaze implements CommandInterface	{

	private Maze maze;

	public IO_OpenMaze(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length == 1 || args.length > 2)	{ throw new IncorrectUsageException(2, args.length); }

		FileInputStream file = null;
		BufferedInputStream bs = null;
		try	{
			file = new FileInputStream(args[1]);
			bs = new BufferedInputStream(file);
			maze.read(bs);
			bs.close();
			file.close();
		} catch (Exception e)	{
			throw new ReadingException(e.getMessage());
		}
	}

	public String description()	{
		return "open - Reads a maze from a given file.\n";
	}

	public String usage()	{
		return "open <maze filename>\n";
	}
}
