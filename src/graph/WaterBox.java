package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;

public class WaterBox extends Box	{

	private boolean deep;

	/** Constructs a WaterBox at coordinates (0, 0, 0), no flag
	 *  and no depth. 
	 *  This box however should not be used unless a call to
	 *  IO method read has been processed succesfully. */
	public WaterBox()	{}
	
	/** Constructs a WaterBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. 
	 *  @param depth indicates whether the waters are deep. */
	public WaterBox(int x, int y, int z, int depth)	{
		super(x, y, z);
		deep = depth != 0;
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)
		throws IOException	{
	
		out.write(context.WATER_ID);
		if(deep)	{
			out.write(1);
		}
		else	{
			out.write(0);
		}
		writeGeneralData(out);
	}

	public void read(InputStream in)
		throws IOException, BadFormatException, ReadingException	{
		ArrayList<Integer> data = readRawData(in);

		if(data.size() != 5)	{
			throw new BadFormatException(data.size(), 5);
		}

		setX(data.get(1));
		setY(data.get(2));
		setZ(data.get(3));
		setFlags(data.get(4));

		deep = data.get(0) != 0;
	}

	public void display()	{
		System.out.print("~");
	}
}
