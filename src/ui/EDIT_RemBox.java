package ui;

import maze.InterfaceableMaze;
import maze.MazeException;


/** RemBox removes a box in the maze. */
public class EDIT_RemBox implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public EDIT_RemBox(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"RemBox		~ Removes a box from the maze.";
	}

	public String usage()	{
		return "rembox <x> <y>";
	}


	/** @throws InvalidArgumentsException if an integer can not be
	 *  read.
	 *  @throws MazeException if the passed coordinates can not be
	 *  reached. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		int x = 0, y = 0;

		try	{
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Coordinates are expected to be integers.");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}

		ui.getMaze().remBox(x, y);
		ui.modify();
	}
}
