package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;
import fileops.BadFormatException;
import fileops.ReadingException;

public class BridgeBox extends Box	{

	/** Constructs a BridgeBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the box. */
	public BridgeBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		int expects = MazeContext.getNbArgs(MazeContext.WALL_ID);
		if(args.length != expects)	{
			throw new InvalidBoxArgumentsException(expects, args.length);
		}
	}

	public int getPracticability()	{
		return 2;
	}

	public void write(OutputStream out)
		throws IOException	{
		
		out.write(MazeContext.BRIDGE_ID);
		writeGeneralData(out);
	}

	public void display(PrintStream out)	{
		out.print("B");
	}
}
