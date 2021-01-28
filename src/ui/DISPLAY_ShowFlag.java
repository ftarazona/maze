package ui;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.MazeException;


/** ShowFlag requires maze to show a flag when displaying. */
public class DISPLAY_ShowFlag implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze. */
	public DISPLAY_ShowFlag(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "show - Displays the flagged boxes of the maze.\n";
	}

	public String usage()	{
		return "show <flag>\n";
	}


	/** @throws UnknownFlagException if the passed flag does not
	 *  match any. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		try	{
			ui.getMaze().show(BoxFlag.valueOf(ui.keyWord(args[1])));
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
	}
}
