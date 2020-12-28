package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;

public class StairsBox extends Box	{

	public final static int STAIRS_DOWNTOP		= 0;
	public final static int STAIRS_LEFTRIGHT	= 1;

	private int dir;

	/** Constructs a StairsBox at coordinates (0, 0, 0), no flag
	 *  and in the vertical direction.
	 *  This box however should not be used unless a call to
	 *  IO method read has been processed succesfully. */
	public StairsBox()	{}
	
	/** Constructs a WaterBox with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the WallBox. 
	 *  @param dir indicates the direction of the stairs. */
	public StairsBox(int x, int y, int z, int dir)	{
		super(x, y, z);
		this.dir = dir;
	}

	public int getPracticability()	{
		return 2;
	}

	public void write(OutputStream out)
		throws IOException	{

		out.write(Box.STAIRS_ID);
		out.write(dir);
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
		setZ(data.get(2));
		setFlags(data.get(4));

		if(data.get(0) != STAIRS_DOWNTOP && data.get(0) != STAIRS_LEFTRIGHT)	{
			throw new ReadingException((char)data.get(0).intValue(), "STAIRS:dir", String.format("%d or %d", STAIRS_DOWNTOP, STAIRS_LEFTRIGHT));
		}

		dir = data.get(0);
	}

	public void display()	{
		System.out.print("/");
	}
}
