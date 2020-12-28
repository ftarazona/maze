package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;

/** This abstract class implements a graph's vertex. It provides
 *  general features such as coordinates or flags. */
public abstract class Box
	implements Vertex, IOInterface	{

	/** An object counter used to ensure each object gets a unique
	 *  ID. */
	private static int nextID = 0;

	public static final char BOX_WALL	= 'W';
	public static final char BOX_EMPTY	= 'E';
	public static final char BOX_STAIRS	= 'S';
	public static final char BOX_WATER	= 'O';
	public static final char BOX_BRIDGE	= 'B';

	public static final int NO_FLAG		= 0;
	public static final int ON_PATH 	= 1;
	public static final int TO_WRITE 	= 2;
	public static final int TO_DRAW 	= 4;
	public static final int START		= 8;
	public static final int END		= 16;
	public static final int MAX_FLAG	= NO_FLAG + ON_PATH + TO_WRITE + TO_DRAW + START + END;

	private int x;
	private int y;
	private int z;
	private final int id;
	private int flags;

	/** Constructs a Box with coordinates (0, 0, 0) and no flag.
	 *  This box however should not be used unless a call to 
	 *  IO method read has been processed successfully. */
	public Box()	{
		this.id = nextID;
		nextID++;
	}

	/** Constructs a Box with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the Box. */
	public Box(int x, int y, int z)	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = nextID;
		nextID++;
		this.flags = NO_FLAG;
	}

	/** Returns the ID of the box.
	 *  @return the ID of the box. */
	public int getID()	{
		return id;
	}

	/** Returns the X coordinate.
	 *  @return the X coordinate. */
	public int getX()	{
		return x;
	}

	/** Sets the X coordinate to a given value. If the value is
	 *  incorrect (i.e. negative), the coordinate is not changed.
	 *  @param x is the coordinate to be set.
	 *  @return true if x was a correct value, false otherwise. */
	public boolean setX(int x)	{
		if(x >= 0)	{ this.x = x; }
		return x >= 0;
	}

	/** Returns the Y coordinate.
	 *  @return the Y coordinate. */
	public int getY()	{
		return y;
	}

	/** Sets the Y coordinate to a given value. If the value is
	 *  incorrect (i.e. negative), the coordinate is not changed.
	 *  @param y is the coordinate to be set.
	 *  @return true if y was a correct value, false otherwise. */
	public boolean setY(int y)	{
		if(y >= 0)	{ this.y = y; }
		return y >= 0;
	}

	/** Returns the Z coordinate.
	 *  @return the Z coordinate. */
	public int getZ()	{
		return z;
	}

	/** Sets the Z coordinate to a given value. If the value is
	 *  incorrect (i.e. negative), the coordinate is not changed.
	 *  @param z is the coordinate to be set.
	 *  @return true if z was a correct value, false otherwise. */
	public boolean setZ(int z)	{
		if(z >= 0)	{ this.z = z; }
		return z >= 0;
	}

	/** Returns the practicability of the box, i.e. the weight
	 *  to be applied in the graph.
	 *  @return the practicability of the box. */
	public abstract int getPracticability();

	/** Returns the flags as an integer.
	 *  @return the flags of the box. */
	public int getFlags()	{
		return flags;
	}

	/** Indicates whether the box carries a given flag.
	 *  @param flag is the flag to be looked for.
	 *  @return true if the box carries the flag, false otherwise.
	 *  If the flag is invalid, returns false. */
	public boolean hasFlag(int flag)	{
		return (flags & flag) != 0;
	}

	/** Sets the flags to a given value. There is no guarantee for
	 *  the value of flags to be under MAX_FLAG.
	 *  @param flags is the new value. */
	public void setFlags(int flags)	{
		this.flags = flags;
	}

	/** Adds a flag to the box. The method does not guarantee the
	 *  new value of flags is under MAX_FLAG.
	 *  @param flag is the flag to be added. It should contain
	 *  only one 1 in its binary form. */
	public void addFlag(int flag)	{
		this.flags |= flag;
	}

	/** Removes a flag to the box if it is carried.
	 *  @param flag is the flag to be removed. */
	public void remFlag(int flag)	{
		this.flags &= ~flag;
	}

	/** Clears all flags. */
	public void clearFlags()	{
		this.flags = NO_FLAG;
	}

	/** Writes the coordinates and the flags of the box in an
	 *  output stream.
	 *  @param out is the output stream in which the values are
	 *  written.
	 *  @throws IOException if an I/O error occurs. */
	protected void writeGeneralData(OutputStream out)	
		throws IOException	{

		out.write(x);
		out.write(y);
		out.write(z);
		out.write(flags);
		out.write(255);
	}

	/** Reads the parameters of a box from an input stream, stops
	 *  when the value 255 or -1 is read.
	 *  @param in is the input stream containing the parameters of
	 *  the box.
	 *  @return a list of parameters.
	 *  @throws IOException if an I/O error occurs. */
	protected ArrayList<Integer> readRawData(InputStream in)
		throws IOException	{

		ArrayList<Integer> data = new ArrayList<Integer>();
		int i = in.read();

		while(i != 255 && i != -1)	{
			data.add(new Integer(i));
			i = in.read();
		}

		return data;
	}

	/** Writes all the parameters of the box in an output stream.
	 *  @param out is the output stream to be written.
	 *  @throws IOException if an I/O error occurs. */
	public abstract void write(OutputStream out)
		throws IOException;

	/** Reads the box from an input stream.
	 *  @param in is the input stream to be read.
	 *  @throws IOException is an I/O error occurs.
	 *  @throws BadFormatException if the number of parameters
	 *  given is incorrect.
	 *  @throws ReadingException if a parameter has an incorrect
	 *  value. */
	public abstract void read(InputStream in)
		throws IOException, BadFormatException, ReadingException;

	public abstract void display();
}
