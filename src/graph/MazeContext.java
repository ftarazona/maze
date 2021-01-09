package graph;

import java.util.HashMap;

public class MazeContext	{
	public static final int NULL_ID 	= 0;
	private static final int NULL_ARGS	= 2;
	public static final int WALL_ID 	= 'W';
	private static final int WALL_ARGS 	= 4;
	public static final int EMPTY_ID 	= 'E';
	private static final int EMPTY_ARGS 	= 4;
	public static final int WATER_ID 	= 'O';
	private static final int WATER_ARGS 	= 5;
	public static final int STAIRS_ID 	= 'S';
	private static final int STAIRS_ARGS 	= 5;
	public static final int BRIDGE_ID 	= 'B';
	private static final int BRIDGE_ARGS 	= 4;

	public static final int MIN_ARGS	= 2;
	public static final int MAX_ARGS	= 5;

	private static final HashMap<String, Integer> boxIDTab;
	private static final HashMap<String, BoxFlag> flagsTab;

	static	{
		boxIDTab 	= new HashMap<String, Integer>();
		flagsTab	= new HashMap<String, BoxFlag>();

		boxIDTab.put("null", 	Integer.valueOf(NULL_ID));
		boxIDTab.put("wall", 	Integer.valueOf(WALL_ID));
		boxIDTab.put("empty", 	Integer.valueOf(EMPTY_ID));
		boxIDTab.put("water", 	Integer.valueOf(WATER_ID));
		boxIDTab.put("stairs", 	Integer.valueOf(STAIRS_ID));
		boxIDTab.put("bridge", 	Integer.valueOf(BRIDGE_ID));

		flagsTab.put("start",	BoxFlag.BOX_START);
		flagsTab.put("end",	BoxFlag.BOX_END);
		flagsTab.put("marked",	BoxFlag.BOX_MARKED);
	}

	public static Box newBox(int boxID, int[] args)	
		throws InvalidBoxArgumentsException	{
	switch(boxID)	{
		case WALL_ID: 		return new WallBox(args);
		case EMPTY_ID: 		return new EmptyBox(args);
		case WATER_ID: 		return new WaterBox(args);
		case STAIRS_ID: 	return new StairsBox(args);
		case BRIDGE_ID: 	return new BridgeBox(args);
		default:		return null;
	}
	}

	public static int getNbArgs(int boxID)	{
	switch(boxID)	{
		case WALL_ID:		return WALL_ARGS;
		case EMPTY_ID:		return EMPTY_ARGS;
		case WATER_ID:		return WATER_ARGS;
		case STAIRS_ID:		return STAIRS_ARGS;
		case BRIDGE_ID:		return BRIDGE_ARGS;
		default:		return NULL_ARGS;
	}
	}

	public static Integer getBoxID(String str)	{
		return boxIDTab.get(str);
	}

	public static BoxFlag getFlag(String str)	{
		return flagsTab.get(str);
	}
}
