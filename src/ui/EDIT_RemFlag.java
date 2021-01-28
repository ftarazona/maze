package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.BoxFlag;
import maze.InterfaceableMaze;
import maze.MazeException;


/** Removes a flag at given coordinates. */
public class EDIT_RemFlag implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_RemFlag(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "remflag - Removes a flag.\n";
	}

	public String usage()	{
		return	"remflag <x> <y> <flag>\n";
	}


	/** @throws InvalidArgumentsException if coordinates can not
	 *  be read.
	 *  @throws MazeException if the passed coordinates can not be
	 *  reached for example. */
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

		ui.getMaze().remFlag(x, y, flag);
		ui.modify();
	}
}
