package ui;

import graph.*;
import dijkstra.*;

public class EDIT_AddFlag implements CommandInterface	{

	private Maze maze;
	private PiFunction pi;
	private PreviousFunction prev;
	private UIContext context;

	public EDIT_AddFlag(Maze maze, PiFunction pi, PreviousFunction prev)	{
		this.maze = maze;
		this.pi = pi;
		this.prev = prev;
		this.context = new UIContext();
		context.setFlagTab();
	}

	public void run(String[] args)	
		throws UIException	{

		if(args.length != 4)	{
			throw new IncorrectUsageException(4, args.length);
		}

		int x = 0, y = 0;
		BoxFlag flag = null;

		try	{
			x = Integer.parseInt(args[1]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[1], 1);
		}
		try	{
			y = Integer.parseInt(args[2]);
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException(args[2], 2);
		}
		
		flag = context.flag(args[3]);
		if(flag.equals(BoxFlag.BOX_START))	{
			pi.clear();
			prev.clear();
		}

		try	{
			maze.addFlag(x, y, flag);
		} catch (MazeOutOfBoundsException e)	{
			throw new UnreachablePositionException(x, y);
		}
	}

	public String description()	{
		return "addflag - Adds or replaces a flag.\n";
	}

	public String usage()	{
		return	"addflag <x> <y> <flag>\n";
	}
}
