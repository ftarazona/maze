package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemCol removes a column in the maze at a given position. */
public class EDIT_RemCol implements CommandInterface	{

	private final UserInterface ui;
	
	/** Constructs the command with specified maze. */
	public EDIT_RemCol(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "remcol - Removes a column to the maze.\n";
	}

	public String usage()	{
		return	"remcol <pos>\n";
	}


	/** @throws InvalidArgumentsException if position can not be
	 *  read.
	 *  @throws MazeException if the passed position can not be
	 *  reached for example. */
	public void run(String[] args)
		throws UIException, MazeException	{

		int pos = 0;

		try	{
			pos = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Position is expected to be an integer.");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}

		ui.getMaze().remCol(pos);
		ui.modify();
	}
}
