package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


/** NOT IMPLEMENTED YET, REQUIRES INTERNAL MODIFICATIONS.
 *  Stairs is a box that forbids a mov to the right/left or up/down.
 *  It also facilitates the move to a certain direction. */
public class StairsBox extends Box	{

	public static int	ID	= 'S';
	private static int	ARGS	= 5;
	private static int	SPRITE_UP	= -1;
	private static int	SPRITE_LEFT	= -1;

	public final static int UP_DOWN		= 0;
	public final static int LEFT_RIGHT	= 1;

	private int dir;

	/** Constructs a StairsBox with specified arguments.
	 *  @param args arguments, must contain 5 arguments.
	 *  @throws InvalidBoxArgumentsException if not given correct
	 *  number of arguments. */
	public StairsBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length != MazeContext.STAIRS_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.STAIRS_ARGS);
		}
		this.dir = args[3] == 0 ? UP_DOWN : LEFT_RIGHT;
		drawid = dir == UP_DOWN ? MazeContext.STAIRS_SPRITE_UP : MazeContext.STAIRS_SPRITE_LEFT;
	}

	public int getPracticability()	{
		return 2;
	}

	public void write(OutputStream out)
		throws IOException	{

		out.write(MazeContext.STAIRS_ID);
		writeGeneralData(out);
		out.write(dir);
	}

	public void display(PrintStream out)	{
		out.print("/");
	}
}
