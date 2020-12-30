package ui;

import java.util.HashMap;
import java.util.Collection;
import graph.Maze;
import graph.BoxContext;

public class UIContext	{
	private HashMap<String, Integer> boxIDs;
	private HashMap<String, CommandInterface> cmdTab;

	public UIContext()	{
	}

	public void setCommandTab(Maze maze)	{
		cmdTab = new HashMap<String, CommandInterface>();
		cmdTab.put("new", new IO_NewMaze(maze));
		cmdTab.put("open", new IO_OpenMaze(maze));
		cmdTab.put("save", new IO_SaveMaze(maze));
		cmdTab.put("display", new DISPLAY_DisplayMaze(maze));
		cmdTab.put("addrow", new EDIT_AddRow(maze));
		cmdTab.put("addcol", new EDIT_AddCol(maze));
		cmdTab.put("remrow", new EDIT_RemRow(maze));
		cmdTab.put("remcol", new EDIT_RemCol(maze));
	}
	
	public void setBoxTab()	{
		boxIDs = new HashMap<String, Integer>();
		boxIDs.put("null", new Integer(BoxContext.NULL_ID));
		boxIDs.put("wall", new Integer(BoxContext.WALL_ID));
		boxIDs.put("empty", new Integer(BoxContext.EMPTY_ID));
		boxIDs.put("water", new Integer(BoxContext.WATER_ID));
		boxIDs.put("bridge", new Integer(BoxContext.BRIDGE_ID));
		boxIDs.put("stairs", new Integer(BoxContext.STAIRS_ID));
	}

	public int boxType(String str)	
		throws UnknownBoxTypeException	{
		Integer intType = boxIDs.get(str);
		if(intType == null)	{
			throw new UnknownBoxTypeException(str);
		}
		return intType.intValue();
	}

	public CommandInterface command(String str)
		throws UnknownCommandException	{
		CommandInterface cmd = cmdTab.get(str);
		if(cmd == null)	{
			throw new UnknownCommandException(str);
		}
		return cmd;
	}

	public Collection<CommandInterface> allCommands()	{
		return cmdTab.values();
	}
}
