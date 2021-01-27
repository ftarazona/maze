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

	private Exception		exception = null;

	public CoreInterface()	{
	}
	
	public void raiseException(Exception e)	{
		exception = e;
	}
	public Exception getException()	{
		Exception e = exception;
		exception = null;
		return e;
	}

	public abstract InterfaceableMaze 	getMaze();
	public abstract Pi			getPi();
	public abstract Previous		getPrevious();

	public abstract void addMaze(int x, int y);
	public abstract void addMaze(String filename);

	public abstract void addScript(String filename);

	public abstract void displayMaze();

	public abstract void run();
}
