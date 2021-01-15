package ui;

import maze.*;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;

public class IO_OpenMaze implements CommandInterface	{

	private InterfaceableMaze maze;

	public IO_OpenMaze(InterfaceableMaze maze)	{
		this.maze = maze;
	}

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

	public String description()	{
		return "open - Reads a maze from a given file.\n";
	}

	public String usage()	{
		return "open <maze filename>\n";
	}
}
