package ui;

import java.util.HashMap;
import java.util.Collection;
import graph.Maze;
import graph.BoxFlag;
import graph.MazeContext;
import dijkstra.*;

public class UIContext	{
	private static HashMap<String, CommandInterface> cmdTab;

	public void setCommandTab(Maze maze, PiFunction pi, PreviousFunction prev)	{

		cmdTab = new HashMap<String, CommandInterface>();
		cmdTab.put("new", 
			new IO_NewMaze(maze));
		cmdTab.put("open",
			new IO_OpenMaze(maze));
		cmdTab.put("save", 
			new IO_SaveMaze(maze));

		cmdTab.put("display", 
			new DISPLAY_DisplayMaze(maze));
		cmdTab.put("displayflag", 
			new DISPLAY_DisplayFlag(maze));
		cmdTab.put("displayflags", 
			new DISPLAY_DisplayFlags(maze));
		cmdTab.put("displayall", 
			new DISPLAY_DisplayAll(maze));

		cmdTab.put("addrow", 
			new EDIT_AddRow(maze));
		cmdTab.put("addcol", 
			new EDIT_AddCol(maze));
		cmdTab.put("remrow", 
			new EDIT_RemRow(maze));
		cmdTab.put("remcol", 
			new EDIT_RemCol(maze));
		cmdTab.put("addbox", 
			new EDIT_AddBox(maze));
		cmdTab.put("rembox", 
			new EDIT_RemBox(maze));
		cmdTab.put("addflag", 
			new EDIT_AddFlag(maze, pi, prev));
		cmdTab.put("remflag", 
			new EDIT_RemFlag(maze, pi, prev));
		cmdTab.put("setroot", 
			new EDIT_SetRoot(maze, pi, prev));
		cmdTab.put("dijkstra", 
			new DIJKSTRA_Dijkstra(maze, pi, prev));
		cmdTab.put("tracepath", 
			new DIJKSTRA_TracePath(maze, pi, prev));
	}
	
	public static int boxType(String str)	
		throws UnknownBoxTypeException	{
		Integer boxID = MazeContext.getBoxID(str);
		if(boxID == null)	{
			throw new UnknownBoxTypeException(str);
		}
		return boxID.intValue();
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

	public static BoxFlag flag(String str)
		throws UnknownFlagException	{
		BoxFlag flag = MazeContext.getFlag(str);
		if(flag == null)	{
			throw new UnknownFlagException(str);
		}
		return flag;
	}
}
