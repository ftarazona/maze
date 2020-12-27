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

	public WaterBox()	{}
	public WaterBox(int x, int y, int z, int depth)	{
		super(x, y, z);
		deep = depth != 0;
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)
		throws IOException	{
	
		out.write(Box.BOX_WATER);
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

		if(data.get(4) > MAX_FLAG)	{
			throw new ReadingException((char)data.get(4).intValue(), "flags", String.format("integer between %d and %d", NO_FLAG, MAX_FLAG));
		}

		setFlags(data.get(4));

		deep = data.get(0) != 0;
	}

	public void display()	{
		System.out.print("~");
	}
}
