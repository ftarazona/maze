package ui;
import graph.*;
import fileops.*;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;

public class IO_SaveMaze implements CommandInterface	{

	private Maze maze;

	public IO_SaveMaze(Maze maze)	{
		this.maze = maze;
	}

	public void run(String[] args)	
		throws UIException	{
		if(args.length == 1 || args.length > 2)	{ throw new IncorrectUsageException(2, args.length); }

		FileOutputStream file = null;
		BufferedOutputStream bs = null;
		try	{
			file = new FileOutputStream(args[1]);
			bs = new BufferedOutputStream(file);
			maze.write(bs);
			bs.close();
			file.close();
		} catch (Exception e)	{
			throw new WritingException(e.getMessage());
		}
	}

	public String description()	{
		return "save - Writes the current maze in given file.\n";
	}

	public String usage()	{
		return "save <filename>\n";
	}
}
