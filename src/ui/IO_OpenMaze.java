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
		if(args.length > 2)	{ throw new IncorrectUsageException(2, args.length); }

		FileInputStream file = null;
		BufferedInputStream bs = null;
		try	{
			file = new FileInputStream(args[1]);
			bs = new BufferedInputStream(file);
			maze.read(bs);
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException(2, args.length);
		} catch (Exception e)	{
			throw new ReadingException(e.getMessage());
		}
	}

	public String usage()	{
		return "open <maze filename>\n";
	}
}
