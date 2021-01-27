package ui;

import java.io.*;
import java.util.*;
import maze.*;
import dijkstra.*;

public abstract class CoreInterface implements UserInterface	{
	
	public final static int	NEW_MAZE 	= 1;
	public final static int	OPEN_MAZE 	= 2;
	public final static int	SAVE_MAZE 	= 3;
	
	public final static int	ADD_ROW 	= 4;
	public final static int	REM_ROW 	= 5;
	public final static int	ADD_COL 	= 6;
	public final static int	REM_COL 	= 7;
	public final static int	ADD_BOX 	= 8;
	public final static int	REM_BOX 	= 9;
	public final static int	ADD_FLAG 	= 10;
	public final static int	REM_FLAG 	= 11;
	public final static int	CLEAR_FLAG 	= 12;
	public final static int	CLEAR_FLAGS 	= 13;
	public final static int	SET_ROOT 	= 14;
	public final static int	REM_ROOT 	= 15;

	public final static int	SHOW_FLAG 	= 16;
	public final static int	SHOW_FLAGS 	= 17;
	public final static int	SHOW_ALL_FLAGS 	= 18;
	public final static int	HIDE_FLAG 	= 19;
	public final static int	HIDE_FLAGS 	= 20;
	public final static int	HIDE_ALL_FLAGS 	= 21;
	public final static int	DISPLAY 	= 22;

	public final static int	RUN_DIJKSTRA 	= 23;
	public final static int	TRACE_PATH 	= 24;

	protected ArrayDeque<Command> 	queue;
	protected ArrayDeque<Command>	scriptsQueue;
	protected ArrayList<Command>	history;
	private Exception		exception = null;

	public CoreInterface()	{
		queue		= new ArrayDeque<Command>();
		scriptsQueue	= new ArrayDeque<Command>();
		history		= new ArrayList<Command>();
	}

	public void 	pushCommand(Command cmd)	{
		queue.offer(cmd);
	}

	public void 	pushScriptCommand(Command cmd)	{
		scriptsQueue.offer(cmd);
	}

	public void 	runCommand()	{
		Command cmd = queue.pop();
		history.add(cmd);
		try	{
			cmd.run();
		} catch(Exception e)	{ raiseException(e); }
	}

	public void 	runScripts()	{
		while(scriptsQueue.isEmpty())	{
			try	{
				scriptsQueue.pop().run();
			} catch (Exception e)	{}
		}
	}

	private void raiseException(Exception e)	{
		exception = e;
	}
	public Exception getException()	{
		Exception e = exception;
		exception = null;
		return e;
	}

	public void generateCommand(int cmdID, Object[] args)	{
		Command cmd = null;
		try	{
		switch(cmdID)	{
			case NEW_MAZE:		cmd = new IO_NewMaze(args, this); 		break;
			case OPEN_MAZE:		cmd = new IO_OpenMaze(args, this); 		break;
			case SAVE_MAZE:		cmd = new IO_SaveMaze(args, this); 		break;
			case ADD_ROW:		cmd = new EDIT_AddRow(args, this); 		break;
			case REM_ROW:		cmd = new EDIT_RemRow(args, this); 		break;
			case ADD_COL:		cmd = new EDIT_AddCol(args, this); 		break;
			case REM_COL:		cmd = new EDIT_RemCol(args, this); 		break;
			case ADD_BOX:		cmd = new EDIT_AddBox(args, this); 		break;
			case REM_BOX:		cmd = new EDIT_RemBox(args, this); 		break;
			case ADD_FLAG:		cmd = new EDIT_AddFlag(args, this); 		break;
			case REM_FLAG:		cmd = new EDIT_RemFlag(args, this); 		break;
			case SET_ROOT:		cmd = new EDIT_SetRoot(args, this); 		break;
			case REM_ROOT:		cmd = new EDIT_RemRoot(args, this); 		break;
			case CLEAR_FLAGS:	cmd = new EDIT_Clear(args, this); 		break;
			case SHOW_FLAG:		cmd = new DISPLAY_ShowFlag(args, this);		break;
			case SHOW_ALL_FLAGS:	cmd = new DISPLAY_ShowFlags(args, this);	break;
			case HIDE_FLAG:		cmd = new DISPLAY_HideFlag(args, this);		break;
			case HIDE_ALL_FLAGS:	cmd = new DISPLAY_HideFlags(args, this);	break;
			case DISPLAY:		cmd = new DISPLAY_DisplayMaze(args, this);	break;
			case RUN_DIJKSTRA:	cmd = new DIJKSTRA_Dijkstra(args, this);	break;
			case TRACE_PATH:	cmd = new DIJKSTRA_TracePath(args, this);	break;
		}
		} catch (IncorrectUsageException e)	{ raiseException(e); }

		pushCommand(cmd);
	}

	public abstract InterfaceableMaze 	getMaze();
	public abstract Pi			getPi();
	public abstract Previous		getPrevious();

	public abstract void addMaze(int x, int y)
		throws MazeException;
	public abstract void addMaze(String filename)
		throws MazeException, IOException;

	public abstract void addScript(String filename);

	public abstract void displayMaze();

	public abstract void run();
}
