package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemRow removes a row in the maze at a given position. */
public class EDIT_RemRow implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public EDIT_RemRow(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"RemRow		~ Removes a row to the maze.";
	}

	public String usage()	{
		return	"remrow <pos>";
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

		ui.getMaze().remRow(pos);
		ui.modify();
	}
}
