package ui;

import java.util.HashMap;
import java.util.Collection;
import graph.Maze;
import graph.BoxFlag;
import graph.BoxContext;
import dijkstra.*;

public class UIContext	{
	private HashMap<String, Integer> boxIDs;
	private HashMap<String, CommandInterface> cmdTab;
	private HashMap<String, BoxFlag> flagTab;

	public UIContext()	{
	}

	public void setCommandTab(Maze maze, PiFunction pi, PreviousFunction prev)	{
		cmdTab = new HashMap<String, CommandInterface>();
		cmdTab.put("new", new IO_NewMaze(maze));
		cmdTab.put("open", new IO_OpenMaze(maze));
		cmdTab.put("save", new IO_SaveMaze(maze));
		cmdTab.put("display", new DISPLAY_DisplayMaze(maze));
		cmdTab.put("displayflag", new DISPLAY_DisplayFlag(maze));
		cmdTab.put("displayflags", new DISPLAY_DisplayFlags(maze));
		cmdTab.put("displayall", new DISPLAY_DisplayAll(maze));
		cmdTab.put("addrow", new EDIT_AddRow(maze));
		cmdTab.put("addcol", new EDIT_AddCol(maze));
		cmdTab.put("remrow", new EDIT_RemRow(maze));
		cmdTab.put("remcol", new EDIT_RemCol(maze));
		cmdTab.put("addbox", new EDIT_AddBox(maze));
		cmdTab.put("rembox", new EDIT_RemBox(maze));
		cmdTab.put("addflag", new EDIT_AddFlag(maze, pi, prev));
		cmdTab.put("remflag", new EDIT_RemFlag(maze, pi, prev));
		cmdTab.put("setroot", new EDIT_SetRoot(maze, pi, prev));
		cmdTab.put("dijkstra", new DIJKSTRA_Dijkstra(maze, pi, prev));
		cmdTab.put("tracepath", new DIJKSTRA_TracePath(maze, pi, prev));
	}
	
	public void setBoxTab()	{
		boxIDs = new HashMap<String, Integer>();
		boxIDs.put("null", Integer.valueOf(BoxContext.NULL_ID));
		boxIDs.put("wall", Integer.valueOf(BoxContext.WALL_ID));
		boxIDs.put("empty", Integer.valueOf(BoxContext.EMPTY_ID));
		boxIDs.put("water", Integer.valueOf(BoxContext.WATER_ID));
		boxIDs.put("bridge", Integer.valueOf(BoxContext.BRIDGE_ID));
		boxIDs.put("stairs", Integer.valueOf(BoxContext.STAIRS_ID));
	}

	public void setFlagTab()	{
		flagTab = new HashMap<String, BoxFlag>();
		flagTab.put("start", BoxFlag.BOX_START);
		flagTab.put("end", BoxFlag.BOX_END);
		flagTab.put("marked", BoxFlag.BOX_MARKED);
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

	public BoxFlag flag(String str)
		throws UnknownFlagException	{
		BoxFlag flag = flagTab.get(str);
		if(flag == null)	{
			throw new UnknownFlagException(str);
		}
		return flag;
	}
}
