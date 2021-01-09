package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;


public class EmptyBox extends Box	{

	/** Constructs an EmptyBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. */
	public EmptyBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		int expects = MazeContext.getNbArgs(MazeContext.WALL_ID);
		if(args.length != expects)	{
			throw new InvalidBoxArgumentsException(expects, args.length);
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

	public void display()	{
		System.out.print("O");
	}
}
