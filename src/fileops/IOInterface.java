package fileops;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public interface IOInterface	{
	public void write(OutputStream out)
		throws IOException;
	public void read(InputStream in)
		throws IOException, BadFormatException, ReadingException;
}
