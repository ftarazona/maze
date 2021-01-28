package ui;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.MazeException;


/** HideFlag requires the maze to hide a flag when displaying. */
public class DISPLAY_HideFlag implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_HideFlag(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "hide - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "hide <flag>\n";
	}


	/** @throws UnknownFlagException if the passed flag does not
	 *  match any. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		try	{
			ui.getMaze().hide(BoxFlag.valueOf(ui.keyWord(args[1])));
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
	}
}
