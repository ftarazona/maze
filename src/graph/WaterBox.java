package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;
import fileops.BadFormatException;
import fileops.ReadingException;

public class WaterBox extends Box	{

	private boolean deep;

	/** Constructs a WaterBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. 
	 *  @param depth indicates whether the waters are deep. */
	public WaterBox(int[] args)	
		throws InvalidBoxArgumentsException	{

		super(args);
		int expects = MazeContext.getNbArgs(MazeContext.WALL_ID);
		if(args.length == expects)	{
			throw new InvalidBoxArgumentsException(expects, args.length);
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
