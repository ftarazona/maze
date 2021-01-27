package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


/** NOT IMPLEMENTED YET, REQUIRES INTERNAL MODIFICATIONS.
 *  A bridge box would allow superposition of two boxes. */
public class BridgeBox extends Box	{

	/** Constructs a BridgeBox with specified arguments.
	 *  @param args arguments, must contain 4 arguments.
	 *  @throws InvalidBoxArgumentsException if not given correct
	 *  number of arguments. */
	public BridgeBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length != MazeContext.BRIDGE_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.BRIDGE_ARGS);
		}
		drawid = MazeContext.BRIDGE_SPRITE;
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
