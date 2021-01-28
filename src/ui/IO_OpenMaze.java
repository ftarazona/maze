package ui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import maze.InterfaceableMaze;
import maze.MazeException;


/** OpenMaze reads a maze from an input stream, typically a file. */
public class IO_OpenMaze implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze. */
	public IO_OpenMaze(UserInterface ui)	{
		this.ui = ui;
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

		FileInputStream file = null;
		BufferedInputStream bs = null;
		try	{
			file = new FileInputStream(args[1]);
			bs = new BufferedInputStream(file);
			ui.getMazeSafe().read(bs);
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
