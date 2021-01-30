package maze;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

import java.util.Arrays;
import java.util.EnumSet;


/** This abstract class implements a graph's vertex. It provides
 *  general features such as coordinates or flags. */
public abstract class Box
	implements Vertex	{

	public static final int		ID	= 0;
	private static final int	ARGS	= 4;
	private static final int	SPRITE	= -1;

	/** An object counter used to ensure each object gets a unique
	 *  ID. */
	private static int nextID = 0;

	private int x;
	private int y;
	private int z;
	private EnumSet<BoxFlag> flags;
	private final int id;
	protected int drawid;


	/** Constructs a Box with given coordinates and no flag.
	 *  @param args an array of integers containing at least the
	 *  coordinates and flags the box is initialized with.
	 *  @throws InvalidBoxArgumentsException if args does not
	 *  provide minimal information required. */
	public Box(int[] args)	
		throws InvalidBoxArgumentsException	{

		if(args.length < ARGS)	{
			throw new InvalidBoxArgumentsException(args.length, ARGS);
		}

		this.x 	= args[0];
		this.y 	= args[1];
		this.z 	= args[2];
		setFlags(args[3]);
		this.id	= nextID;
		nextID++;
		this.drawid	= SPRITE;
	}

	/** Returns a new box given arguments.
	  * @param args contains the box type and the arguments to
	  * pass the constructor.
	  * @throws InvalidBoxArgumentsException if the given
	  * arguments could not be parsed by the Box constructor.
	  * @throws UnexpectedBoxTypeException if the given box type
	  * does not match with any. */
	public static Box newBox(int[] args)
		throws InvalidBoxArgumentsException, UnexpectedBoxTypeException	{
		int boxType = args[0];
		int[] boxArgs = Arrays.copyOfRange(args, 1, args.length);
		switch(boxType)	{
		case WallBox.ID:	return new WallBox(boxArgs);
		case EmptyBox.ID:	return new EmptyBox(boxArgs);
		case WaterBox.ID:	return new WaterBox(boxArgs);
		case ID:		return null;
		default:		throw new UnexpectedBoxTypeException(boxType);
		}
	}

	/** Returns the ID of the box.
	 *  @return the ID of the box. */
	public int getID()	{
		return id;
	}

	/** Returns the X coordinate.
	 *  @return The X coordinate. */
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

	/** Returns the practicability of the box.
	 *  @return The practicability of the box. */
	public abstract int getPracticability();

	/** Returns the set of flags carried by the box.
	 *  @return The set of flags carried by the box. */
	public EnumSet<BoxFlag> getFlags()	{
		return flags;
	}

	/** Indicates whether the box carries a given flag.
	 *  @param flag is the flag to be checked.
	 *  @return true if the box carries the flag, false otherwise.
	 *  If the flag is invalid, returns false. */
	public boolean hasFlag(BoxFlag flag)	{
		return flags.contains(flag);
	}

	/** Indicates whether the box carries any flag.
	 *  @return true if the box carries at least one flag, false
	 *  otherwise. */
	public boolean hasNoFlag()	{
		return flags.isEmpty();
	}

	/** Sets the flags to a given value. There is no guarantee for
	 *  the value of flags to be under MAX_FLAG.
	 *  @param flags new flags to be carried. */
	public void setFlags(EnumSet<BoxFlag> flags)	{
		this.flags = flags;
	}

	/** Sets the flags to a given value. 
	 *  If value contains bits not corresponding to any flag, it
	 *  is ignored.
	 *  @param value new integer value of flags to be carried. */
	public void setFlags(int value)	{
		this.flags = BoxFlag.IntToBoxFlags(value);
	}

	/** Adds a flag to the box.
	 *  @param flag flag to be added. */
	public void addFlag(BoxFlag flag)	{
		flags.add(flag);
	}

	/** Removes a flag to the box.
	 *  @param flag flag to be removed. */
	public void remFlag(BoxFlag flag)	{
		flags.remove(flag);
	}

	/** Clears all flags. */
	public void clearFlags()	{
		this.flags.clear();
	}

	/** Writes the coordinates and the flags of the box in an
	 *  output stream.
	 *  @param out output stream in which the values are written.
	 *  @throws IOException if an I/O error occurs. */
	protected void writeGeneralData(OutputStream out)	
		throws IOException	{

		out.write(x);
		out.write(y);
		out.write(z);
		out.write(BoxFlag.BoxFlagsToInt(flags));
	}

	/** Writes all the parameters of the box in an output stream.
	 *  @param out output stream to be written.
	 *  @throws IOException if an I/O error occurs. */
	public abstract void write(OutputStream out)
		throws IOException;

	/** Displays the box in an output stream.
	 *  @param out output stream to be written. */
	public abstract void display(PrintStream out);

	/** Returns the ID used for drawing the box in a tiled map.
	 *  @return The drawing ID of the box. */
	public int drawID()	{ return drawid; }
}
