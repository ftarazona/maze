package maze;

import java.util.EnumSet;

/** This enumerated type describes a set of flags carried by a Box
 *  instance. */
public enum BoxFlag	{

	BOX_START,
	BOX_END,
	BOX_MARKED;

	/** Converts the flag to an integer.
	 *  The flag is described by a position, and its integer value
	 *  is given by a binary form.
	 *  @return The integer value of the flag. */
	public int toInt()	{
		return 1 << this.ordinal();
	}

	/** Converts a set of flags to an integer.
	 *  It is the result of a OR operation on every integer
	 *  values of the flags.
	 *  @param flags the set of flags to be converted.
	 *  @return The integer value value describing the set of
	 *  flags. */
	static public int BoxFlagsToInt(EnumSet<BoxFlag> flags)	{
		int value = 0;
		for(BoxFlag flag: flags)	{
			value |= flag.toInt();
		}
		return value;
	}

	/** Converts an integer to a set of flags.
	 *  If the integer contains bits not corresponding to any
	 *  flag, the bit is ignored.
	 *  @param value the integer to be converted.
	 *  @return The set of flags corresponding. */
	static public EnumSet<BoxFlag> IntToBoxFlags(int value)	{
		EnumSet<BoxFlag> flags = EnumSet.noneOf(BoxFlag.class);
		for(BoxFlag flag: BoxFlag.values())	{
			if((value & flag.toInt()) != 0) 	{
				flags.add(flag); 
			}
		}
		return flags;
	}
}
