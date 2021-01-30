package ui;

import java.util.*;
import java.io.*;

import dijkstra.*;
import maze.*;
import graphics.*;

import java.awt.*;
import javax.swing.*;

/** Implementation of a user interface */
public class PromptInterface implements UserInterface	{
	
	private DrawFrame window	= null;

	//These booleans record various states of the interface
	private boolean quitValue		= false;
	private boolean recordingScript		= false;
	private boolean modified		= false;
	private boolean windowSetUp		= false;

	//notToParse indicates which commands do not use the interface
	//parser but their own instead.
	private String notToParse	= "times";

	//The queues allow a flexible flow when executing the commands
	//They come with priorities : first the interface will execute
	//every command in iteration, then main, then script.
	private Queue<String> iterationQueue	= new ArrayDeque<String>();
	private Queue<String> mainQueue		= new ArrayDeque<String>();
	private Stack<String> scriptStack	= new Stack<String>();

	//These collections are used to record previous executed commands
	private Queue<String> recordQueue	= new ArrayDeque<String>();
	private ArrayList<String> history	= new ArrayList<String>();

	//Some variables are said to be special, such as maze properties,
	//in order to be used without having to modify them manually.
	private HashMap<String, String> specialVars
		= new HashMap<String, String>();
	private HashMap<String, String> vars
		= new HashMap<String, String>();

	private static HashMap<String, Integer> keywords
		= new HashMap<String, Integer>();

	private InterfaceableMaze maze	= new Maze();
	private Pi pi			= new PiFunction();
	private Previous previous	= new PreviousFunction();
	private PrintStream out		= System.out;
	private Scanner scanner		= new Scanner(System.in);

	private static ArrayList<CommandInterface> commandList
		= new ArrayList<CommandInterface>();
	private static HashMap<String, CommandInterface> commands
		= new HashMap<String, CommandInterface>();

	/* Links keywords to values */
	static	{
		keywords.put("wall", Integer.valueOf(WallBox.ID));
		keywords.put("empty", Integer.valueOf(EmptyBox.ID));
		keywords.put("water", Integer.valueOf(WaterBox.ID));

		keywords.put("root", Integer.valueOf(BoxFlag.BOX_START.toInt()));
		keywords.put("start", Integer.valueOf(BoxFlag.BOX_START.toInt()));
		keywords.put("end", Integer.valueOf(BoxFlag.BOX_END.toInt()));
		keywords.put("marked", Integer.valueOf(BoxFlag.BOX_MARKED.toInt()));
	}
	
	/** Constructs a new PromptInterface, links the commands. */
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
		commandList.add(new INFO_Area(this));
		commandList.add(new UI_Times(this));

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
		commands.put("area",		commandList.get(34));
		commands.put("size",		commandList.get(34));
		commands.put("times",		commandList.get(35));
	
		window = new DrawFrame(this);
		setWindowUp();
	}

	/** Refresh window */
	public void setWindowUp()	{
		window.refresh();
		windowSetUp = true;
	}

	/** Closes the window */
	public void closeWindow()	{
		window.dispose();
		windowSetUp = false;
	}

	/** Set the focus on the window */
	public void focusWindow()	{
		window.toFront();
	}

/* ********************************************************************* */
/* ****************************** Execution **************************** */
/* ********************************************************************* */

	/** Runs the interface.
	  * In a PromptInterface, th		window.setVisible(false);
	  windowSetUp = false;is loops until the user quits.
	  * In this loop, the interface empty its command queues, then
	  * asks the user to feed it with a new one. */
	public void run(String[] args)	{
		while(!quitValue)	{
			while(executeCommand());

			//This reads a command from direct user input
			getOutStream().print(">>> ");
			offer(scanner.nextLine());
			executeCommand();
		}
	}

	/** Executes next command.
	  * The command must have been pushed to a queue before
	  * calling this method, hence a previous call to offer or
	  * offerScript is required.
	  * This method does not throw exceptions, as it treats each of
	  * them.
	  * @return true if a command was found, false otherwise. */
	public boolean 		executeCommand()	{
		String cmdStr = new String();

		//Implements the priority of queues
		if(!iterationQueue.isEmpty())	{
			cmdStr = iterationQueue.poll();
		} else if(!mainQueue.isEmpty())	{
			cmdStr = mainQueue.poll();
		} else if(!scriptStack.isEmpty())	{
			cmdStr = scriptStack.pop();
		} else	{
			return false;
		}

		String[] args = cmdStr.toLowerCase().split(" ");
		boolean toRecord = recordingScript && !(args[0].equals("*"));
		//Removes the * meaning the command is not to be recorded
		if(args[0].equals("*"))	{ args = Arrays.copyOfRange(args, 1, args.length); }

		CommandInterface cmd = null;

		try	{
			if(!cmdStr.isEmpty())	{
				cmd = fetchCommand(args[0]);
				if(!notToParse.contains(args[0]))	{
					args = parse(args);
				}
				cmd.run(args);
			}
			if(toRecord)	{ recordQueue.offer(cmdStr); }

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
				"Please report this bug to " +
				"florian.tarazona@telecom-paris.fr\n",
				e.getMessage()));
			e.printStackTrace();
		}
		return true;
	}

/* ********************************************************************* */
/* **************************** Flow Control *************************** */
/* ********************************************************************* */

	/** Pushes a command to the main queue (commands entered by
	  * the user directly. It does not check whether the command
	  * can be executed.
	  * @param cmd the command to push. */
	public void offer(String cmd)	{
		mainQueue.offer(cmd);
	}

	/** Pushes a command to the script queue (commands found in a
	  * script. It does not check whether the command
	  * can be executed.
	  * @param cmd the command to push. */
	public void offerScript(String cmd)	{
		scriptStack.push(cmd);
	}

	/** Pushes a command to the iteration queue (commands entered by
	  * times). It does not check whether the command
	  * can be executed.
	  * @param cmd the command to push. */
	public void offerIteration(String cmd)	{
		iterationQueue.offer(cmd);
	}

/* ********************************************************************* */
/* *********************** Manipulation of commands ******************** */
/* ********************************************************************* */

	/** Fetches a command given a string.
	  * @param str command name to search for.
	  * @return the command corresponding.
	  * @throws UnknownCommandException if the name did not match
	  * any command name defined by the interface. */
	public CommandInterface	fetchCommand(String str)
		throws UnknownCommandException	{

		CommandInterface cmd = commands.get(str);
		if(cmd == null)	{
			throw new UnknownCommandException(str);
		}
		return cmd;
	}

	/** Returns the list of commands linked to the interface.
	  * @return the list of commands. */
	public ArrayList<CommandInterface> getCommandList()	{
		return commandList;
	}

	/** Indicates whether a string matches a command.
	  * @param cmd command name to check.
	  * @return true if cmd matches a command, false otherwise. */
	public boolean isCommand(String cmd)	{
		return commands.get(cmd) != null;
	}

/* ********************************************************************* */
/* *********************** Declare & fetch Variables ******************* */
/* ********************************************************************* */

	/** Initializes a variable.
	  * @param variable label of the new variable.
	  * @param value value of the new variable. */
	public void setVariable(String variable, String value)	{
		if(variable.matches("height|width|area"))	{
			System.out.println("WARNING : Attempting to override special variables. Aborting...");
			return;
		}
		vars.put(variable, value);
	}

	/** Returns a list of current variables associated with their
	  * values.
	  * @return a HashMap with a list of the variables. */
	public HashMap<String, String> getVariables()	{
		return vars;
	}

	/** Returns a list of special variables associated with their
	  * values.
	  * @return a HashMap with a list of special variables. */
	public HashMap<String, String> getSpecialVariables()	{
		return specialVars;
	}

	/** Fetches a variable.
	  * @param variable label of the variable to search for.
	  * @return the value of the variable.
	  * @throws UninitializedVariableException if the variable was
	  * never set via a call to setVariable. */
	public String fetchVariable(String variable)	
		throws UninitializedVariableException	{
		String value = vars.get(variable);
		if(value == null)	{
			value = specialVars.get(variable);
			if(value == null)	{
				throw new UninitializedVariableException(variable);
			} else	{
				return value;
			}
		} else	{
			return value;
		}
	}

	/** Returns the integer corresponding to a key word of the
	  * interface.
	  * @param key keyword to search for.
	  * @return the integer corresponding.
	  * @throws UnexpectedKeyWordException if the keyword is not
	  * defined by the interface. */
	public int keyWord(String key)
		throws UnexpectedKeyWordException	{

		Integer ret = keywords.get(key);
		if(ret == null)	{
			throw new UnexpectedKeyWordException(key);
		}
		return ret.intValue();
	}

	/** Parses str to find variable names to replace by values.
	  * @param str string to parse.
	  * @return parsed string
	  * @throws UninitializedVariableException if a variable could
	  * not be found. */
	public String[] parse(String[] cargs)	
		throws UIException	{

		ArrayList<String> args = new ArrayList<String>(Arrays.asList(cargs));

		for(int i = 0; i < args.size(); i++)	{
			String w = args.get(i);
			if(w.startsWith("$"))	{
				//We replace the label by its value
				args.remove(i);
				args.add(i, fetchVariable(w.substring(1)));
			}
		}

		for(int i = 0; i < args.size(); i++)	{
			String w = args.get(i);
			if(w.equals("random"))	{
				try	{
				//The next arguments must be the min/max
				int min = Integer.parseInt(args.get(i+1));
				int max = Integer.parseInt(args.get(i+2));
				int r = (int)(Math.random() * (max-min) + min);
				args.remove(i+2);
				args.remove(i+1);
				args.remove(i);
				args.add(i, Integer.toString(r));
				} catch (IndexOutOfBoundsException e)	{
					throw new UIException("Usage of random : random <min> <max>");
				} catch (NumberFormatException e)	{
					throw new UIException("random expects integer values for min and max.");
				}
			}
		}
		
		//Generating the new array from an ArrayList
		String[] ret = new String[args.size()];
		for(int i = 0; i < args.size(); i++)
			ret[i] = args.get(i);
		return ret;
	}

/* ********************************************************************* */
/* ***************** Control of the State of the Interface ************* */
/* ********************************************************************* */

	/** Indicates the interface the user quits.
	  * The interface may not effectively quit on call to this
	  * method. */
	public void quit()		{ quitValue = true; }

	/** Indicates the interface to record next commands from
	  * direct user input. 
	  * The previous record is deleted on call to this method. */
	public void startRecording()	{ 
		recordQueue.clear();
		recordingScript = true; 
	}

	/** Indicates the interface to stop recording. */
	public void stopRecording()	{ recordingScript = false; }

	/** Returns the last record.
	  * @return a queue containing the record. */
	public Queue<String> getRecord()	{ return recordQueue; }

	/** Indicates the maze has been modified to the interface. */
	public void modify()		{ 
		modified = true;
		specialVars.put("height", Integer.toString(maze.getHeight()));
		specialVars.put("width", Integer.toString(maze.getWidth()));
		specialVars.put("area", Integer.toString(maze.getArea()));
		if(windowSetUp)
			window.refresh();
	}

	/** Indicates the maze has been saved to the interface. */
	public void save()		{ modified = false; }

	/** Processes some specific actions on maze opening. */
	public void open()	{
		specialVars.put("height", Integer.toString(maze.getHeight()));
		specialVars.put("width", Integer.toString(maze.getWidth()));
		specialVars.put("area", Integer.toString(maze.getArea()));
		if(windowSetUp)
			window.refresh();
	}

	/** Processes some specific actions on maze closure. */
	public void close()	{
		modified = false;
		specialVars.remove("height");
		specialVars.remove("width");
		specialVars.remove("area");

		if(windowSetUp)
			window.refresh();
	}

	/** Indicated whether the maze has unsaved modifications.
	  * @return true if the maze was modified since last save, 
	  * false otherwise. */
	public boolean wasModified()	{ return modified; }

/* ********************************************************************* */
/* ************************* Getters and Writers *********************** */
/* ********************************************************************* */

	/** Returns the current maze.
	  * @return the current maze.
	  * @throws NoMazeOpenedException if no maze is opened. */
	public InterfaceableMaze 	getMaze()	
		throws NoMazeOpenedException	{ 
		if(maze.isOpened())
			return maze;
		else
			throw new NoMazeOpenedException();
	}

	/** Returns the current maze assuming the calling method will
	  * not attempt to modify with an access method.
	  * @return the current maze.
	  * @throws NullMazeException if anything kept the maze to be
	  * built with default constructor. */
	public InterfaceableMaze	getMazeSafe()
		throws NullMazeException	{
		if(maze != null)
			return maze;
		else
			throw new NullMazeException();
	}

	/** Returns Pi function used in Dijkstra algorithm.
	  * @return Pi function. */
	public Pi			getPi()		{ return pi; }
	
	/** Returns Previous function used in Dijkstra algorithm.
	  * @return Previous function. */
	public Previous		getPrevious()	{ return previous; }

	/** Returns the default stream used by the interface as the
	  * output.
	  * @return the output stream of the interface. */
	public PrintStream	getOutStream()		{ return out; }

	/** Writes in the output.
	  * @param str string to be written. */
	public void		print(String str)	{ out.print(str); }
	
	/** Writes in the output and adds a new line character.
	  * @param str string to be written. */
	public void		println(String str)	{ out.println(str); }
}
