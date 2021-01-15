package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


public class BridgeBox extends Box	{

	/** Constructs a BridgeBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the box. */
	public BridgeBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length != MazeContext.BRIDGE_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.BRIDGE_ARGS);
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
