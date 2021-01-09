package graph;

import java.util.ArrayList;
import java.util.EnumSet;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import fileops.BadFormatException;
import fileops.ReadingException;

/** This abstract class implements a graph's vertex. It provides
 *  general features such as coordinates or flags. */
public abstract class Box
	implements Vertex	{

	/** An object counter used to ensure each object gets a unique
	 *  ID. */
	private static int nextID = 0;

	private int x;
	private int y;
	private int z;
	private EnumSet<BoxFlag> flags;
	private final int id;


	/** Constructs a Box with given coordinates and no flag.
	 *  @param x, y, z are the coordinates of the Box. */
	public Box(int[] args)	
		throws InvalidBoxArgumentsException	{

		if(args.length < 4)	{
			throw new InvalidBoxArgumentsException(3, args.length);
		}

		this.x = args[0];
		this.y = args[1];
		this.z = args[2];
		setFlags(args[3]);
		this.id = nextID;
		nextID++;
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
	public EnumSet<BoxFlag> getFlags()	{
		return flags;
	}

	/** Indicates whether the box carries a given flag.
	 *  @param flag is the flag to be looked for.
	 *  @return true if the box carries the flag, false otherwise.
	 *  If the flag is invalid, returns false. */
	public boolean hasFlag(BoxFlag flag)	{
		return flags.contains(flag);
	}

	public boolean hasNoFlag()	{
		return flags.isEmpty();
	}

	/** Sets the flags to a given value. There is no guarantee for
	 *  the value of flags to be under MAX_FLAG.
	 *  @param flags is the new value. */
	public void setFlags(EnumSet<BoxFlag> flags)	{
		this.flags = flags;
	}

	public void setFlags(int value)	{
		this.flags = BoxFlag.IntToBoxFlags(value);
	}

	/** Adds a flag to the box. The method does not guarantee the
	 *  new value of flags is under MAX_FLAG.
	 *  @param flag is the flag to be added. It should contain
	 *  only one 1 in its binary form. */
	public void addFlag(BoxFlag flag)	{
		flags.add(flag);
	}

	/** Removes a flag to the box if it is carried.
	 *  @param flag is the flag to be removed. */
	public void remFlag(BoxFlag flag)	{
		flags.remove(flag);
	}

	/** Clears all flags. */
	public void clearFlags()	{
		this.flags.clear();
	}

	public void displayFlag(BoxFlag flag)	{
		if(hasFlag(flag))	{
			System.out.print("X");
		}
	}

	public void displayFlags()	{
		if(hasFlag(BoxFlag.BOX_START))	{
			System.out.print("S");
		} else if(hasFlag(BoxFlag.BOX_END))	{
			System.out.print("E");
		} else if(hasFlag(BoxFlag.BOX_MARKED))	{
			System.out.print("X");
		} else	{}
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
		out.write(BoxFlag.BoxFlagsToInt(flags));
	}

	/** Writes all the parameters of the box in an output stream.
	 *  @param out is the output stream to be written.
	 *  @throws IOException if an I/O error occurs. */
	public abstract void write(OutputStream out)
		throws IOException;

	public abstract void display();
}
