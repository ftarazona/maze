package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


/** Box of variable practicability finite if shallow, infinite if
 *  deep. */
public class WaterBox extends Box	{

	public static int	ID	= 'O';
	private static int	ARGS	= 5;
	private static int	SPRITE_SHALLOW	= 1;
	private static int	SPRITE_DEEP	= 1;

	private boolean deep;

	/** Constructs a WaterBox with specified arguments.
	 *  @param args arguments, must contain 5 arguments.
	 *  @throws InvalidBoxArgumentsException if not given correct
	 *  number of arguments. */
	public WaterBox(int[] args)	
		throws InvalidBoxArgumentsException	{

		super(args);
		if(args.length != ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, ARGS);
		}
		deep = args[3] != 0;
		drawid = deep ? SPRITE_DEEP : SPRITE_SHALLOW;
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)
		throws IOException	{
	
		out.write(ID);
		writeGeneralData(out);
		out.write(deep ? 1 : 0);
	}

	public void display(PrintStream out)	{
		out.print("~");
	}
}
