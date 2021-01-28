package ui;

import maze.Box;
import maze.InterfaceableMaze;
import maze.MazeException;

/** AddCol adds a column to the ui at given position. */
public class EDIT_AddCol implements CommandInterface	{
	
	private final UserInterface ui;

	/** Constructs the command with specified ui. */
	public EDIT_AddCol(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "addcol - Adds a column to the ui.\n";
	}

	public String usage()	{
		return	"1. addcol <pos>\n" +
			"2. addcol <pos> <type>\n";
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
		int[] boxArgs = new int[args.length + 2];

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
			throw new InvalidArgumentsException("Box options are expected to be integers");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}

		ui.getMaze().addCol(pos, boxArgs);
	}
}
