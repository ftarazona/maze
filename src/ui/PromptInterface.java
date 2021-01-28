package ui;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

import java.io.PrintStream;
import java.io.IOException;

import dijkstra.*;
import maze.*;


/** Implementation of a user interface */
public class PromptInterface extends CoreInterface implements UserInterface	{
	
	private InterfaceableMaze maze	= new Maze();
	private Pi pi			= new PiFunction();
	private Previous previous	= new PreviousFunction();
	private PrintStream out		= System.out;
	private Scanner scanner		= new Scanner(System.in);

	private static HashMap<String, Integer> keywords
		= new HashMap<String, Integer>();
	private HashMap<String, CommandInterface> commands
		= new HashMap<String, CommandInterface>();

	static	{
		keywords.put("wall", Integer.valueOf(WallBox.ID));
		keywords.put("empty", Integer.valueOf(EmptyBox.ID));
		keywords.put("water", Integer.valueOf(WaterBox.ID));

		keywords.put("root", Integer.valueOf(BoxFlag.BOX_START.toInt()));
		keywords.put("start", Integer.valueOf(BoxFlag.BOX_START.toInt()));
		keywords.put("end", Integer.valueOf(BoxFlag.BOX_END.toInt()));
		keywords.put("marked", Integer.valueOf(BoxFlag.BOX_MARKED.toInt()));
	}
	
	/** Constructs a new PromptInterface. */
	public PromptInterface()	{
		CommandInterface quit		= new UI_Quit(this);
		CommandInterface loadScript	= new UI_LoadScript(this);
		CommandInterface showscript	= new UI_DisplayScript(this);
		CommandInterface record		= new UI_Record(this);
		CommandInterface savescript	= new UI_SaveScript(this);
		CommandInterface help		= new INFO_Help(this);
		CommandInterface usage		= new INFO_Usage(this);
		CommandInterface height		= new INFO_Height(this);
		CommandInterface width		= new INFO_Width(this);
		CommandInterface newmaze	= new IO_NewMaze(this);
		CommandInterface openmaze	= new IO_OpenMaze(this);
		CommandInterface savemaze	= new IO_SaveMaze(this);
		CommandInterface close		= new IO_CloseMaze(this);
		CommandInterface display	= new DISPLAY_DisplayMaze(this);
		CommandInterface draw		= new DISPLAY_DrawMaze(this);
		CommandInterface showflag	= new DISPLAY_ShowFlag(this);
		CommandInterface showflags	= new DISPLAY_ShowFlags(this);
		CommandInterface hideflag	= new DISPLAY_HideFlag(this);
		CommandInterface hideflags	= new DISPLAY_HideFlags(this);
		CommandInterface dijkstra	= new DIJKSTRA_Dijkstra(this);
		CommandInterface tracepath	= new DIJKSTRA_TracePath(this);
		CommandInterface addrow		= new EDIT_AddRow(this);
		CommandInterface addcol		= new EDIT_AddCol(this);
		CommandInterface addbox		= new EDIT_AddBox(this);
		CommandInterface addflag	= new EDIT_AddFlag(this);
		CommandInterface remrow		= new EDIT_RemRow(this);
		CommandInterface remcol		= new EDIT_RemCol(this);
		CommandInterface rembox		= new EDIT_RemBox(this);
		CommandInterface remflag	= new EDIT_RemFlag(this);
		CommandInterface setroot	= new EDIT_SetRoot(this);
		CommandInterface remroot	= new EDIT_RemRoot(this);
		CommandInterface clear		= new EDIT_Clear(this);

		commands.put("quit", 		quit);
		commands.put("exit", 		quit);
		commands.put("script",		loadScript);
		commands.put("showscript",	showscript);
		commands.put("displayscript",	showscript);
		commands.put("record",		record);
		commands.put("savescript",	savescript);
		commands.put("help", 		help);
		commands.put("usage", 		usage);
		commands.put("height",		height);
		commands.put("width",		width);
		commands.put("new",		newmaze);
		commands.put("newmaze",		newmaze);
		commands.put("open",		openmaze);
		commands.put("openmaze",	openmaze);
		commands.put("save",		savemaze);
		commands.put("savemaze",	savemaze);
		commands.put("close",		close);
		commands.put("closemaze",	close);
		commands.put("display", 	display);
		commands.put("draw", 		draw);
		commands.put("showflag", 	showflag);
		commands.put("showflags", 	showflags);
		commands.put("hideflag", 	hideflag);
		commands.put("hideflags", 	hideflags);
		commands.put("dijkstra", 	dijkstra);
		commands.put("tracepath", 	tracepath);
		commands.put("addrow", 		addrow);
		commands.put("addcol", 		addcol);
		commands.put("addbox", 		addbox);
		commands.put("addflag", 	addflag);
		commands.put("remrow", 		remrow);
		commands.put("remcol", 		remcol);
		commands.put("rembox", 		rembox);
		commands.put("remflag", 	remflag);
		commands.put("setroot",		setroot);
		commands.put("remroot",		remroot);
		commands.put("clear",		clear);
	}

	public void run(String[] args)	{
		while(!hasQuitted())	{
			while(executeCommand());
			getOutStream().print(">>> ");
			offer(scanner.nextLine());
			executeCommand();
		}
	}

	public CommandInterface	fetchCommand(String str)
		throws UnknownCommandException	{

		CommandInterface cmd = commands.get(str);
		if(cmd == null)	{
			throw new UnknownCommandException(str);
		}
		return cmd;
	}

	public boolean isCommand(String cmd)	{
		return commands.get(cmd) != null;
	}

	public int keyWord(String key)
		throws UnexpectedKeyWordException	{

		Integer ret = keywords.get(key);
		if(ret == null)	{
			throw new UnexpectedKeyWordException(key);
		}
		return ret.intValue();
	}

	public InterfaceableMaze 	getMaze()	
		throws NoMazeOpenedException	{ 
		if(maze.isOpened())
			return maze;
		else
			throw new NoMazeOpenedException();
	}
	public InterfaceableMaze	getMazeSafe()
		throws NullMazeException	{
		if(maze != null)
			return maze;
		else
			throw new NullMazeException();
	}
	public Pi			getPi()		{ return pi; }
	public Previous		getPrevious()	{ return previous; }
	public PrintStream		getOutStream()	{ return out; }
}
