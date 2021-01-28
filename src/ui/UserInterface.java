package ui;

import maze.*;
import dijkstra.*;
import java.util.*;
import java.io.*;

public interface UserInterface	{

	/** Runs the interface.
	 *  @param args arguments passed to be treated when launching
	 *  the interface. */
	public void run(String[] args);

	public void 			offer(String cmd);
	public void 			offerScript(String cmd);
	public void			startRecording();
	public void			stopRecording();
	public Queue<String>		getRecord();
	public CommandInterface 	fetchCommand(String str)
		throws UnknownCommandException;
	public boolean 			executeCommand();
	public int 			keyWord(String key)
		throws UnexpectedKeyWordException;
	public InterfaceableMaze 	getMaze();
	public Pi	 		getPi();
	public Previous		 	getPrevious();
	public PrintStream 		getOutStream();
	public void 			quit();
	public boolean 			hasQuitted();
}
