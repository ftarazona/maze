package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

/** Basic box with infinite practicability. */
public class WallBox extends Box	{

	public static int	ID	= 'W';
	private static int	ARGS	= 4;
	private static int	SPRITE	= 3;

	/** Constructs a WallBox with specified arguments.
	 *  @param args arguments, must contain 4 arguments.
	 *  @throws InvalidBoxArgumentsException if not given correct
	 *  number of arguments. */
	public WallBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length != ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, ARGS);
		}
		drawid = SPRITE;
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)	
		throws IOException	{

		out.write(ID);
		writeGeneralData(out);
	}

	public void display(PrintStream out)	{
		out.print("W");
	}
}
