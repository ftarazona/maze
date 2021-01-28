package ui;

import maze.Box;
import maze.InterfaceableMaze;
import maze.MazeException;


/** NewMaze creates a new maze. */
public class IO_NewMaze implements CommandInterface	{
	
	private UserInterface ui;

	/** Constructs the command with specified maze. */
	public IO_NewMaze(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "new - Creates a new maze with given dimensions.\n";
	}

	public String usage()	{
		return 	"1. new <>\n" +
			"2. new <height> <width>\n" +
			"3. new <height> <width> <boxtype>\n";
	}


	/** @throws InvalidArgumentsException if dimensions can not
	 *  be read.
	 *  @throws UnknownBoxTypeException if the passed box type
	 *  does not match any.
	 *  @throws MazeException if Maze can not create the new maze.
	 */
	public void run(String[] args)	
		throws UIException, MazeException	{
	
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
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Width, height and box options are expected to be integers.");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
		
		ui.getMazeSafe().newMaze(height, width, boxArgs);
	}
}
