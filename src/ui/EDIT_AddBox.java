package ui;

import maze.EmptyBox;
import maze.InterfaceableMaze;
import maze.MazeException;


/** AddBox adds a box in the maze */
public class EDIT_AddBox implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public EDIT_AddBox(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Addbox 		~ Adds or replaces a box of the maze.";
	}

	public String usage()	{
		return	"1. addbox <x> <y> (empty)\n" +
			"2. addbox <x> <y> <type>";
	}


	/** @throws InvalidArgumentsException if an integer value can
	 *  not be read.
	 *  @throws UnknownBoxTypeException if the box type given does
	 *  not match any.
	 *  @throws MazeException if an error occured in Maze class,
	 *  such as trying to reach out of bounds coordinates. */
	public void run(String[] args)
		throws UIException, MazeException	{
		
		int[] boxArgs = new int[args.length + 2];

		try	{
			if(args.length >= 4)	{
				boxArgs[0] = ui.keyWord(args[3]);
			} else	{
				boxArgs[0] = EmptyBox.ID;
			}
			boxArgs[1] = Integer.parseInt(args[1]);
			boxArgs[2] = Integer.parseInt(args[2]);
			boxArgs[3] = 0;
			boxArgs[4] = 0;
			for(int i = 4; i < args.length; i++)	{
				boxArgs[i+1] = Integer.parseInt(args[i]);
			}
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Coordinates and box options are expected to be integers");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}

		ui.getMaze().addBox(boxArgs);
		ui.modify();
	}
}
