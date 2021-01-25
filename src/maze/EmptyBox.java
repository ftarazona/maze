package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


/** Basic box with maximum practicability. */
public class EmptyBox extends Box	{

	/** Constructs an EmptyBox with specified arguments.
	 *  @param args arguments, must contain 4 arguments.
	 *  @throws InvalidBoxArgumentsException if not given correct
	 *  number of arguments. */
	public EmptyBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length != MazeContext.EMPTY_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.EMPTY_ARGS);
		}
		drawid = MazeContext.EMPTY_SPRITE;
	}

	public int getPracticability()	{
		return 1;
	}

	public void write(OutputStream out)
		throws IOException	{
		
		out.write(MazeContext.EMPTY_ID);
		writeGeneralData(out);
	}

	public void display(PrintStream out)	{
		out.print("O");
	}
}
