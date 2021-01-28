package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemCol removes a column in the maze at a given position. */
public class EDIT_RemCol implements CommandInterface	{

	private final PromptInterface ui;
	
	/** Constructs the command with specified maze. */
	public EDIT_RemCol(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"RemCol		~ Removes a column to the maze.";
	}

	public String usage()	{
		return	"remcol <pos>";
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
