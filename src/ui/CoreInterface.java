package ui;

import java.util.*;
import java.io.*;
import maze.*;
import dijkstra.*;

public abstract class CoreInterface implements UserInterface	{

	public CoreInterface()	{
	}

	public abstract CommandInterface	fetchCommand(String str)
		throws UnknownCommandException;
	public abstract int 			keyWord(String key)
		throws UnexpectedKeyWordException;

	public abstract InterfaceableMaze 	getMaze()
		throws NoMazeOpenedException;
	public abstract InterfaceableMaze 	getMazeSafe()
		throws NullMazeException;
	public abstract Pi 			getPi();
	public abstract Previous	 	getPrevious();
	public abstract PrintStream 		getOutStream();

	public abstract void run(String[] args);
}
