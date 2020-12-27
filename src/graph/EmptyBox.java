package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;


public class EmptyBox extends Box	{

	public EmptyBox()	{}
	public EmptyBox(int x, int y, int z)	{
		super(x, y, z);
	}

	public int getPracticability()	{
		return 1;
	}

	public void write(OutputStream out)
		throws IOException	{
		
		out.write(Box.BOX_EMPTY);
		writeGeneralData(out);
	}

	public void read(InputStream in)
		throws IOException, BadFormatException, ReadingException	{
		
		ArrayList<Integer> data = readRawData(in);

		if(data.size() != 4)	{
			throw new BadFormatException(4, data.size());
		}

		setX(data.get(0));
		setY(data.get(1));
		setZ(data.get(2));

		if(data.get(3) > MAX_FLAG)	{
			throw new ReadingException((char)data.get(3).intValue(), "flags", String.format("integer between %d and %d", NO_FLAG, MAX_FLAG));
		}

		setFlags(data.get(3));
	}
}
