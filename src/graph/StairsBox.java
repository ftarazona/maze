package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.BadFormatException;
import fileops.ReadingException;

public class StairsBox extends Box	{

	public final static int UP_DOWN		= 0;
	public final static int LEFT_RIGHT	= 1;

	private int dir;

	/** Constructs a WaterBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. 
	 *  @param dir indicates the direction of the stairs. */
	public StairsBox(int[] args)	
		throws InvalidBoxArgumentsException	{
		super(args);
		int expects = MazeContext.getNbArgs(MazeContext.WALL_ID);
		if(args.length != expects)	{
			throw new InvalidBoxArgumentsException(expects, args.length);
		}
		this.dir = args[3] == 0 ? UP_DOWN : LEFT_RIGHT;
	}

	public int getPracticability()	{
		return 2;
	}

	public void write(OutputStream out)
		throws IOException	{

		out.write(MazeContext.STAIRS_ID);
		writeGeneralData(out);
		out.write(dir);
	}

	public void display()	{
		System.out.print("/");
	}
}
