package ui;

import maze.Box;
import maze.InterfaceableMaze;
import maze.MazeException;

/** AddCol adds a column to the ui at given position. */
public class EDIT_AddCol implements CommandInterface	{
	
	private final PromptInterface ui;

	/** Constructs the command with specified ui. */
	public EDIT_AddCol(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "AddCol 		~ Adds a column to the maze.";
	}

	public String usage()	{
		return	"1. addcol <pos>\n" +
			"2. addcol <pos> <type>";
	}


	/** @throws InvalidArgumentsException if an integer value can
	 *  not be read.
	 *  @throws UnknownBoxTypeException if the box type given does
	 *  not match any.
	 *  @throws MazeException if an error occured in Maze class,
	 *  such as trying to reach out of bounds coordinates. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		int pos = 0;
		int[] boxArgs = new int[args.length + 5];

		try	{
			pos = Integer.parseInt(args[1]);
			if(args.length >= 3)	{
				boxArgs[0] = ui.keyWord(args[2]);
			} else	{
				boxArgs[0] = Box.ID;
			}
			boxArgs[1] = 0;
			boxArgs[2] = 0;
			boxArgs[3] = 0;
			boxArgs[4] = 0;
			for(int i = 3; i < args.length; i++)	{
				boxArgs[i+2] = Integer.parseInt(args[i]);
			}
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Position and box options are expected to be integers");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}

		ui.getMaze().addCol(pos, boxArgs);
		ui.modify();
	}
}
