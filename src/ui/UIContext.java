package ui;

import java.util.Queue;
import java.util.HashMap;
import java.util.Collection;
import java.io.PrintStream;
import graph.InterfaceableMaze;
import graph.BoxFlag;
import graph.MazeContext;
import dijkstra.*;

public class UIContext	{
	private static String[] cmdLst = {
		"help",
		"usage",

		"script",

		"new", 
		"open", 
		"save",

		"display", 
		"show", 
		"showflags", 
		"hide",
		"hideflags",

		"addrow", 
		"addcol", 
		"remrow",
		"remcol",
		"addbox",
		"rembox",
		"addflag",
		"remflag",
		"clear",
		"setroot",
		"remroot",

		"dijkstra",
		"tracepath"
	};

	private HashMap<String, CommandInterface> cmdTab;

	public void setCommandTab(InterfaceableMaze maze, PiFunction pi, PreviousFunction prev, Queue<String> queue, PrintStream ostream)	{

		cmdTab = new HashMap<String, CommandInterface>();
		
		cmdTab.put("help",
			new INFO_Help(this, ostream));
		cmdTab.put("usage",
			new INFO_Usage(this, ostream));
		
		cmdTab.put("script",
			new UI_LoadScript(queue));

		cmdTab.put("new", 
			new IO_NewMaze(maze));
		cmdTab.put("open",
			new IO_OpenMaze(maze));
		cmdTab.put("save", 
			new IO_SaveMaze(maze));

		cmdTab.put("display", 
			new DISPLAY_DisplayMaze(maze, ostream));
		cmdTab.put("show", 
			new DISPLAY_ShowFlag(maze));
		cmdTab.put("showflags", 
			new DISPLAY_ShowFlags(maze));
		cmdTab.put("hide", 
			new DISPLAY_HideFlag(maze));
		cmdTab.put("hideflags",
			new DISPLAY_HideFlags(maze));

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
		cmdTab.put("clear",
			new EDIT_Clear(maze, pi, prev));
		cmdTab.put("setroot", 
			new EDIT_SetRoot(maze, pi, prev));
		cmdTab.put("remroot",
			new EDIT_RemRoot(maze, pi, prev));

		cmdTab.put("dijkstra", 
			new DIJKSTRA_Dijkstra(maze, pi, prev));
		cmdTab.put("tracepath", 
			new DIJKSTRA_TracePath(maze, pi, prev));
	}
	
	public static int boxType(String str)	
		throws UnknownBoxTypeException	{
		Integer boxType = MazeContext.getBoxType(str);
		if(boxType == null)	{
			throw new UnknownBoxTypeException(str);
		}
		return boxType.intValue();
	}

	public CommandInterface command(String str)
		throws UnknownCommandException	{
		CommandInterface cmd = cmdTab.get(str);
		if(cmd == null)	{
			throw new UnknownCommandException(str);
		}
		return cmd;
	}

	public static BoxFlag flag(String str)
		throws UnknownFlagException	{
		BoxFlag flag = MazeContext.getFlag(str);
		if(flag == null)	{
			throw new UnknownFlagException(str);
		}
		return flag;
	}

	public String usage(String str)
		throws UnknownCommandException	{
		String ret = "\n";
		ret = ret.concat(command(str).usage());
		ret = ret.concat("\n");

		return ret;
	}

	public String help()	{
		String ret = 	"\nHere is some help." +
				"\nType \"usage <command>\" " +
				"for further information.\n\n";

		for(int i = 0; i < cmdLst.length; i++)	{
			ret = ret.concat(cmdTab.get(cmdLst[i]).description());
		}
		ret = ret.concat("\n");

		return ret;
	}
}
