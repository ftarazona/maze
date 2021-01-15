package ui;
import graph.*;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class IO_SaveMaze implements CommandInterface	{

	private Maze maze;

	public IO_SaveMaze(Maze maze)	{
		this.maze = maze;
	}

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

	public String description()	{
		return "save - Writes the current maze in given file.\n";
	}

	public String usage()	{
		return "save <filename>\n";
	}
}
