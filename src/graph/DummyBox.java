package graph;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.BadFormatException;
import fileops.ReadingException;
import fileops.IOInterface;

public class DummyBox extends Box	{

	public DummyBox()	{}
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
}
