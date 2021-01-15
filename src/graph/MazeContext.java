package graph;

import java.util.HashMap;

public class MazeContext	{
	public static final int NULL_ID 	= 0;
	static final int NULL_ARGS	= 3;
	static final int WALL_ID 	= 'W';
	static final int WALL_ARGS 	= 4;
	static final int EMPTY_ID 	= 'E';
	static final int EMPTY_ARGS 	= 4;
	static final int WATER_ID 	= 'O';
	static final int WATER_ARGS 	= 5;
	static final int STAIRS_ID 	= 'S';
	static final int STAIRS_ARGS 	= 5;
	static final int BRIDGE_ID 	= 'B';
	static final int BRIDGE_ARGS 	= 4;

	public static final int MIN_ARGS	= 3;
	public static final int MAX_ARGS	= 5;

	private static final HashMap<String, Integer> boxTypeTab;
	private static final HashMap<String, BoxFlag> flagsTab;

	static	{
		boxTypeTab 	= new HashMap<String, Integer>();
		flagsTab	= new HashMap<String, BoxFlag>();

		boxTypeTab.put("null", 	Integer.valueOf(NULL_ID));
		boxTypeTab.put("wall", 	Integer.valueOf(WALL_ID));
		boxTypeTab.put("empty", Integer.valueOf(EMPTY_ID));
		boxTypeTab.put("water", Integer.valueOf(WATER_ID));
		boxTypeTab.put("stairs",Integer.valueOf(STAIRS_ID));
		boxTypeTab.put("bridge",Integer.valueOf(BRIDGE_ID));

		flagsTab.put("start",	BoxFlag.BOX_START);
		flagsTab.put("end",	BoxFlag.BOX_END);
		flagsTab.put("marked",	BoxFlag.BOX_MARKED);
	}

	public static Box newBox(int boxType, int[] args)	
		throws InvalidBoxArgumentsException, UnexpectedBoxTypeException	{
	switch(boxType)	{
		case WALL_ID: 		return new WallBox(args);
		case EMPTY_ID: 		return new EmptyBox(args);
		case WATER_ID: 		return new WaterBox(args);
		case STAIRS_ID: 	return new StairsBox(args);
		case BRIDGE_ID: 	return new BridgeBox(args);
		case 0:			return null;
		default:		throw new UnexpectedBoxTypeException(boxType);
	}
	}

	public static int getNbArgs(int boxType)	
		throws UnexpectedBoxTypeException	{
	switch(boxType)	{
		case WALL_ID:		return WALL_ARGS;
		case EMPTY_ID:		return EMPTY_ARGS;
		case WATER_ID:		return WATER_ARGS;
		case STAIRS_ID:		return STAIRS_ARGS;
		case BRIDGE_ID:		return BRIDGE_ARGS;
		case 0:			return NULL_ARGS;
		default:		throw new UnexpectedBoxTypeException(boxType);
	}
	}

	public static Integer getBoxType(String str)	{
		return boxTypeTab.get(str);
	}

	public static BoxFlag getFlag(String str)	{
		return flagsTab.get(str);
	}
}
