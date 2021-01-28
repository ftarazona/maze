package ui;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.MazeException;


/** HideFlag requires the maze to hide a flag when displaying. */
public class DISPLAY_HideFlag implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_HideFlag(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Hide 		~ Displays the flagged boxes of the maze.";
	}

	public String usage()	{
		return "hide <flag>";
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
