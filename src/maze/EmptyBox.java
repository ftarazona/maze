package maze;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class EmptyBox extends Box	{

	/** Constructs an EmptyBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. */
	public EmptyBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		if(args.length != MazeContext.EMPTY_ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, MazeContext.EMPTY_ARGS);
		}
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
