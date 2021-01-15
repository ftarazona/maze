package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class WallBox extends Box	{

	/** Constructs a WallBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. */
	public WallBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length != MazeContext.WALL_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.WALL_ARGS);
		}
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)	
		throws IOException	{

		out.write(MazeContext.WALL_ID);
		writeGeneralData(out);
	}

	public void display(PrintStream out)	{
		out.print("W");
	}
}
