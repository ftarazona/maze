package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.MazeException;


/** AddFlag adds a flag to a given box in maze. */
public class EDIT_AddFlag implements CommandInterface	{

	private final PromptInterface ui;

	/** Construcs the command with specified maze, pi and previous
	 *  functions. */
	public EDIT_AddFlag(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "AddFlag 	~ Adds or replaces a flag.";
	}

	public String usage()	{
		return	"addflag <x> <y> <flag>";
	}


	/** @throws InvalidArgumentsException if an integer value can
	 *  not be read.
	 *  @throws UnknownFlagException if the flag given does
	 *  not match any.
	 *  @throws MazeException if an error occured in Maze class,
	 *  such as trying to reach out of bounds coordinates. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		int x = 0, y = 0;
		BoxFlag flag = null;

		try	{
			x = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
			flag = BoxFlag.valueOf(ui.keyWord(args[3]));
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("Coordinates are expected to be integers.");
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}

		if(flag.equals(BoxFlag.BOX_START))	{
			ui.getPi().clear();
			ui.getPrevious().clear();
		}

		ui.getMaze().addFlag(x, y, flag);
		ui.modify();
	}
}
