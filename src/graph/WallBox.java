package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;

public class WallBox extends Box	{

	/** Constructs a WallBox at coordinates (0, 0, 0) and no flag.
	 *  This box however should not be used unless a call to IO
	 *  method read has been processed succesfully. */
	public WallBox()	{}

	/** Constructs a WallBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. */
	public WallBox(int x, int y, int z)	{
		super(x, y, z);
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)	
		throws IOException	{

		out.write(context.WALL_ID);
		writeGeneralData(out);
	}

	public void read(InputStream in)
		throws IOException, BadFormatException, ReadingException	{
		ArrayList<Integer> data = readRawData(in);
		
		if(data.size() != 4)	{
			throw new BadFormatException(data.size(), 4);
		}
		
		setX(data.get(0));
		setY(data.get(1));
		setZ(data.get(2));
		setFlags(data.get(3));
	}

	public void display()	{
		System.out.print("W");
	}
}
