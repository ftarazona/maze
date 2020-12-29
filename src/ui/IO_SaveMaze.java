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
		if(args.length > 2)	{ throw new IncorrectUsageException(2, args.length); }

		FileOutputStream file = null;
		BufferedOutputStream bs = null;
		try	{
			file = new FileOutputStream(args[1]);
			bs = new BufferedOutputStream(file);
			maze.write(bs);
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException(2, args.length);
		} catch (Exception e)	{
			throw new WritingException(e.getMessage());
		}
	}

	public String usage()	{
		return "save <filename>\n";
	}
}
