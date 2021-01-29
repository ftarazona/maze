package ui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Scanner;

import maze.InterfaceableMaze;
import maze.MazeException;


/** OpenMaze reads a maze from an input stream, typically a file. */
public class IO_OpenMaze implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public IO_OpenMaze(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Open 		~ Reads a maze from a given file.";
	}

	public String usage()	{
		return "open <maze filename>";
	}


	/** @throws IOException if an I/O error occured.
	 *  @throws MazeException if the file can not be parsed. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		if(!args[0].contains("safe"))	{
		boolean closeAnyway = false;
		boolean answerOK = false;
		while(ui.wasModified() && !answerOK)	{
			System.out.println("The current was modified and not saved. Do you want to open a new one anyway ? (y/n) ");
			Scanner scanner = new Scanner(System.in);
			String answer = scanner.nextLine().toLowerCase();
			if(answer.matches("y|yes|n|no"))	{
				answerOK = true;
				closeAnyway = answer.matches("y|yes");
			}
		}

		if(!closeAnyway)	{ return; }
		}

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

	private static void compatibilityMode(BufferedInputStream bs, InterfaceableMaze maze)	
		throws MazeException	{
		System.out.println("Compatibility mode");
	}
}
