package ui;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.InterfaceableMaze;
import maze.MazeException;


/** SetRoot sets a new root in the maze. */
public class EDIT_SetRoot implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_SetRoot(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "setroot - Sets the position of the root\n";
	}

	public String usage()	{
		return	"setroot <x> <y>\n";
	}


	/** @throws InvalidArgumentsException if position can not be
	 *  read. */
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

		ui.getMaze().setRoot(x, y);
		ui.modify();
	}
}
