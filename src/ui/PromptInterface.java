package ui;

import java.util.*;
import java.io.*;

import dijkstra.*;
import maze.*;


/** Implementation of a user interface */
public class PromptInterface implements UserInterface	{
	
	private boolean quitValue		= false;
	private boolean recordingScript		= false;
	private boolean modified		= false;
	private Queue<String> mainQueue		= new ArrayDeque<String>();
	private Queue<String> scriptQueue	= new ArrayDeque<String>();
	private Queue<String> recordQueue	= new ArrayDeque<String>();
	private ArrayList<String> history	= new ArrayList<String>();

	private HashMap<String, String> vars	= new HashMap<String, String>();

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
		CommandInterface declare	= new UI_DeclareVariable(this);
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
		commands.put("var",		declare);
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

	public boolean 		executeCommand()	{
		String cmdStr = new String();
		if(!mainQueue.isEmpty())	{
			cmdStr = mainQueue.poll();
		} else if(!scriptQueue.isEmpty())	{
			cmdStr = scriptQueue.poll();
			history.add(cmdStr);
		} else	{
			return false;
		}

		String[] args = cmdStr.toLowerCase().split(" ");
		boolean toRecord = !args[0].equals("*") && !args[0].equals("record");
		if(args[0].equals("*"))	{
			args = Arrays.copyOfRange(args, 1, args.length);
		}

		CommandInterface cmd = null;

		try	{
			for(int i = 0; i < args.length; i++)	{
				int len = args[i].length();
				if(len > 0 && args[i].charAt(0) == '$')	{
					args[i] = fetchVariable(args[i].substring(1));
				}
			}

			if(!cmdStr.isEmpty())	{
				cmd = fetchCommand(args[0]);
				cmd.run(args);
			}
			if(recordingScript && toRecord)	{
				recordQueue.offer(cmdStr);
			}
		} catch (IncorrectUsageException e)	{
			getOutStream().print(String.format(
				"Wrong usage :\n%s\n", 
				cmd.usage()));
		} catch (NoMazeOpenedException e)	{
			getOutStream().print(e.getMessage() + "\n");
		} catch (UIException e)	{
			getOutStream().print(String.format("An error occured while trying to run a command: %s\n", e.getMessage()));
		} catch (MazeException e)	{
			getOutStream().print(String.format("An error occured while running a command: %s\n", e.getMessage()));
		} catch (Exception e)	{
			getOutStream().print(String.format(
				"An internal error occured : \n" +
				"%s\n" +
				"Please report the bug to " +
				"florian.tarazona@telecom-paris.fr\n",
				e.getMessage()));
			e.printStackTrace();
		}
		return true;
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

	public void offer(String cmd)	{
		mainQueue.offer(cmd);
	}

	public void offerScript(String cmd)	{
		scriptQueue.offer(cmd);
	}

	public void setVariable(String variable, String value)	{
		vars.put(variable, value);
	}
	public String fetchVariable(String variable)	
		throws UninitializedVariableException	{
		String value = vars.get(variable);
		if(value == null)	{ 
			throw new UninitializedVariableException(variable);
		} else	{
			return value;
		}
	}

	public void quit()		{ quitValue = true; }
	public boolean hasQuitted()	{ return quitValue; }

	public void startRecording()	{ 
		recordQueue.clear();
		recordingScript = true; 
	}
	public void stopRecording()	{ recordingScript = false; }
	public Queue<String> getRecord()	{ return recordQueue; }

	public void modify()		{ modified = true; }
	public void save()		{ modified = false; }
	public boolean wasModified()	{ return modified; }

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
