package graph;

import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.IOInterface;
import fileops.BadFormatException;
import fileops.ReadingException;

/*
 * The class below implements the Vertex interface.
 *
 * Although the course states it might be an abstract class extended
 *  by different kind of boxes, I think there are not enough 
 *  significant differences that may justify the need for an abstract
 *  class.
 * Therefore, the class adds an attribute describing the category of
 *  the box to allow the graph to estimate the distances.
 */

public abstract class Box
	implements Vertex, IOInterface	{

	/* defaultID indicates there is no Box.
	 * It can prove useful when making recursive functions for
	 *  instance.
	 */

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

	/* A box is unique by its coordinates. Therefore we need three
	 *  numbers to give an ID :
	 * * the two coordinates.
	 * * the maximum of the first one.
	 * Then the ID is x * maxx + y and a euclidian division allows
	 *  to find back x and y. Moreover, if maxx is minimal, the
	 *  IDs given are successive, which allows making arrays in
	 *  which index and IDs can be identified, with an optimal
	 *  cost in memory. */

	private int x;
	private int y;
	private int z;
	private final int id;
	private int flags;

	/* Default constructor
	 *  the ID by default is -1, which is maxx = 0, x = 0, y = -1.
	 *  the BoxType is EMPTY. */

	public Box()	{id = -1;}

	public Box(int x, int y, int z)	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = nextID;
		nextID++;
		this.flags = NO_FLAG;
	}

	public int getID()	{
		return id;
	}

	public int getX()	{
		return x;
	}

	protected void setX(int x)	{
		this.x = x;
	}

	public int getY()	{
		return y;
	}

	protected void setY(int y)	{
		this.y = y;
	}

	public int getZ()	{
		return z;
	}

	protected void setZ(int z)	{
		this.z = z;
	}

	public abstract int getPracticability();

	public int getFlags()	{
		return flags;
	}

	public boolean hasFlag(int flag)	{
		return (flags & flag) != 0;
	}

	public void setFlags(int flags)	{
		this.flags = flags;
	}

	public void addFlag(int flag)	{
		this.flags |= flag;
	}

	public void remFlag(int flag)	{
		this.flags &= ~flag;
	}

	public void clearFlags()	{
		this.flags = NO_FLAG;
	}

	protected void writeGeneralData(OutputStream out)	
		throws IOException	{

		out.write(x);
		out.write(y);
		out.write(z);
		out.write(flags);
		out.write(255);
	}

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

	public abstract void write(OutputStream out)
		throws IOException;
	public abstract void read(InputStream in)
		throws IOException, BadFormatException, ReadingException;

	public abstract void display();
}
