package ui;

import java.util.Scanner;

import maze.Box;
import maze.InterfaceableMaze;
import maze.MazeException;


/** NewMaze creates a new maze. */
public class IO_NewMaze implements CommandInterface	{
	
	private PromptInterface ui;

	/** Constructs the command with specified maze. */
	public IO_NewMaze(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"New 		~ Creates a new maze with given dimensions.";
	}

	public String usage()	{
		return 	"1. new <>\n" +
			"2. new <height> <width>\n" +
			"3. new <height> <width> <boxtype>";
	}


	/** @throws InvalidArgumentsException if dimensions can not
	 *  be read.
	 *  @throws UnknownBoxTypeException if the passed box type
	 *  does not match any.
	 *  @throws MazeException if Maze can not create the new maze.
	 */
	public void run(String[] args)	
		throws UIException, MazeException	{
	
		if(!args[0].contains("safe"))	{
		boolean closeAnyway = true;
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

		int width = 0;
		int height = 0;
		int[] boxArgs = new int[args.length + 5];

		try	{
			if(args.length >= 3)	{
				height = Integer.parseInt(args[1]);
				width = Integer.parseInt(args[2]);
			}
			if(args.length >= 4)	{
				boxArgs[0] = ui.keyWord(args[3]);
			} else	{
				boxArgs[0] = Box.ID;
			}
			boxArgs[1] = 0;
			boxArgs[2] = 0;
			boxArgs[3] = 0;
			boxArgs[4] = 0;
			for(int i = 4; i < args.length; i++)	{
				boxArgs[i+1] = Integer.parseInt(args[i]);
			}

			ui.open();
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Width, height and box options are expected to be integers.");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
		
		ui.getMazeSafe().newMaze(height, width, boxArgs);
	}
}
