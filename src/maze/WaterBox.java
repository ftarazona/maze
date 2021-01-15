package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

public class WaterBox extends Box	{

	private boolean deep;

	/** Constructs a WaterBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. 
	 *  @param depth indicates whether the waters are deep. */
	public WaterBox(int[] args)	
		throws InvalidBoxArgumentsException	{

		super(args);
		if(args.length != MazeContext.WATER_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.WATER_ARGS);
		}
		deep = args[3] != 0;
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
