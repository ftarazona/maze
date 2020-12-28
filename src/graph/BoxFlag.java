package graph;

import java.util.EnumSet;

public enum BoxFlag	{
	BOX_NO_FLAG,
	BOX_START,
	BOX_END,
	BOX_MARKED;

	public int toInt()	{
		return 1 << this.ordinal();
	}

	static public int MAX_FLAG()	{
		return BOX_NO_FLAG.toInt() + BOX_START.toInt() + BOX_END.toInt() + BOX_MARKED.toInt();
	}

	static public int BoxFlagsToInt(EnumSet<BoxFlag> flags)	{
		int value = 0;
		for(BoxFlag flag: flags)	{
			value |= flag.toInt();
		}
		return value;
	}

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
