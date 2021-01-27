package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


/** Box of variable practicability finite if shallow, infinite if
 *  deep. */
public class WaterBox extends Box	{

	private boolean deep;

	/** Constructs a WaterBox with specified arguments.
	 *  @param args arguments, must contain 5 arguments.
	 *  @throws InvalidBoxArgumentsException if not given correct
	 *  number of arguments. */
	public WaterBox(int[] args)	
		throws InvalidBoxArgumentsException	{

		super(args);
		if(args.length != MazeContext.WATER_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.WATER_ARGS);
		}
		deep = args[3] != 0;
		drawid = deep ? MazeContext.WATER_SPRITE_DEEP : MazeContext.WATER_SPRITE_SHALLOW;
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)
		throws IOException	{
	
		out.write(MazeContext.WATER_ID);
		writeGeneralData(out);
		out.write(deep ? 1 : 0);
	}

	public void display(PrintStream out)	{
		out.print("~");
	}
}
