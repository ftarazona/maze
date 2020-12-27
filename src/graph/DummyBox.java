package graph;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.BadFormatException;
import fileops.ReadingException;
import fileops.IOInterface;

public class DummyBox extends Box	{

	/** Constructs a DummyBox at coordinates (0, 0, 0) and no
	 *  flag. This box however should not be used unless a call to
	 *  IO method read has been processed succesfully. */
	public DummyBox()	{}
	
	/** Constructs a DummyBox with given coordinates and no flag.
	 *  @param x, y are the coordinates of the box. */
	public DummyBox(int x, int y)	{
		super(x, y, 0);
	}

	public int getPracticability()	{
		return Graph.distant;
	}

	public void write(OutputStream out)	
		throws IOException {}
	public void read(InputStream in)
		throws IOException, BadFormatException, ReadingException {}

	public void display()	{
		System.out.print("_");
	}
}
