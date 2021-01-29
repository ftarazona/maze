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
	private ArrayList<CommandInterface> commandList
		= new ArrayList<CommandInterface>();
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
		commandList.add(new UI_Quit(this));
		commandList.add(new UI_LoadScript(this));
		commandList.add(new UI_DisplayScript(this));
		commandList.add(new UI_Record(this));
		commandList.add(new UI_SaveScript(this));
		commandList.add(new UI_DeclareVariable(this));
		commandList.add(new INFO_Help(this));
		commandList.add(new INFO_Usage(this));
		commandList.add(new INFO_Height(this));
		commandList.add(new INFO_Width(this));
		commandList.add(new IO_NewMaze(this));
		commandList.add(new IO_OpenMaze(this));
		commandList.add(new IO_SaveMaze(this));
		commandList.add(new IO_CloseMaze(this));
		commandList.add(new DISPLAY_DisplayMaze(this));
		commandList.add(new DISPLAY_DrawMaze(this));
		commandList.add(new DISPLAY_ShowFlag(this));
		commandList.add(new DISPLAY_ShowFlags(this));
		commandList.add(new DISPLAY_HideFlag(this));
		commandList.add(new DISPLAY_HideFlags(this));
		commandList.add(new DIJKSTRA_Dijkstra(this));
		commandList.add(new DIJKSTRA_TracePath(this));
		commandList.add(new EDIT_AddRow(this));
		commandList.add(new EDIT_AddCol(this));
		commandList.add(new EDIT_AddBox(this));
		commandList.add(new EDIT_AddFlag(this));
		commandList.add(new EDIT_RemRow(this));
		commandList.add(new EDIT_RemCol(this));
		commandList.add(new EDIT_RemBox(this));
		commandList.add(new EDIT_RemFlag(this));
		commandList.add(new EDIT_SetRoot(this));
		commandList.add(new EDIT_RemRoot(this));
		commandList.add(new EDIT_Clear(this));
		commandList.add(new UI_ListVariables(this));

		commands.put("quit", 		commandList.get(0));
		commands.put("exit", 		commandList.get(0));
		commands.put("script",		commandList.get(1));
		commands.put("showscript",	commandList.get(2));
		commands.put("displayscript",	commandList.get(2));
		commands.put("record",		commandList.get(3));
		commands.put("savescript",	commandList.get(4));
		commands.put("var",		commandList.get(5));
		commands.put("help", 		commandList.get(6));
		commands.put("usage", 		commandList.get(7));
		commands.put("height",		commandList.get(8));
		commands.put("width",		commandList.get(9));
		commands.put("new",		commandList.get(10));
		commands.put("newmaze",		commandList.get(10));
		commands.put("newsafe",		commandList.get(10));
		commands.put("newmazesafe",	commandList.get(10));
		commands.put("open",		commandList.get(11));
		commands.put("openmaze",	commandList.get(11));
		commands.put("opensafe",	commandList.get(11));
		commands.put("openmazesafe",	commandList.get(11));
		commands.put("save",		commandList.get(12));
		commands.put("savemaze",	commandList.get(12));
		commands.put("savesafe",	commandList.get(12));
		commands.put("savemazesafe",	commandList.get(12));
		commands.put("close",		commandList.get(13));
		commands.put("closemaze",	commandList.get(13));
		commands.put("display", 	commandList.get(14));
		commands.put("draw", 		commandList.get(15));
		commands.put("showflag", 	commandList.get(16));
		commands.put("showflags", 	commandList.get(17));
		commands.put("hideflag", 	commandList.get(18));
		commands.put("hideflags", 	commandList.get(19));
		commands.put("dijkstra", 	commandList.get(20));
		commands.put("tracepath", 	commandList.get(21));
		commands.put("addrow", 		commandList.get(22));
		commands.put("addcol", 		commandList.get(23));
		commands.put("addbox", 		commandList.get(24));
		commands.put("addflag", 	commandList.get(25));
		commands.put("remrow", 		commandList.get(26));
		commands.put("remcol", 		commandList.get(27));
		commands.put("rembox", 		commandList.get(28));
		commands.put("remflag", 	commandList.get(29));
		commands.put("setroot",		commandList.get(30));
		commands.put("remroot",		commandList.get(31));
		commands.put("clear",		commandList.get(32));
		commands.put("variables",	commandList.get(33));
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

	public ArrayList<CommandInterface> getCommandList()	{
		return commandList;
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
	public HashMap<String, String> getVariables()	{
		return vars;
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

	public PrintStream	getOutStream()		{ return out; }
	public void		print(String str)	{ out.print(str); }
	public void		println(String str)	{ out.println(str); }
}
