package ui;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import maze.InterfaceableMaze;
import maze.MazeException;


/** SaveMaze writes maze in an output stream. */
public class IO_SaveMaze implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze. */
	public IO_SaveMaze(UserInterface ui)	{
		this.ui = ui;
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

		FileOutputStream file = null;
		BufferedOutputStream bs = null;
		try	{
			file = new FileOutputStream(args[1]);
			bs = new BufferedOutputStream(file);
			ui.getMaze().write(bs);
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		} finally	{
			try	{
				if(bs != null)
					bs.close();
				if(file != null)
					file.close();
			} catch (IOException e)	{
				throw new UIException(e.getMessage());
			}
		}
	}
}
