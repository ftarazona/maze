package ui;

import java.util.*;
import java.io.*;
import maze.*;
import dijkstra.*;

public abstract class CoreInterface implements UserInterface	{

	private boolean quitValue		= false;
	private boolean recordingScript		= false;
	private Queue<String> mainQueue		= new ArrayDeque<String>();
	private Queue<String> scriptQueue	= new ArrayDeque<String>();
	private Queue<String> recordQueue	= new ArrayDeque<String>();
	protected ArrayList<String> history	= new ArrayList<String>();

	public CoreInterface()	{
	}

	public void offer(String cmd)	{
		mainQueue.offer(cmd);
	}

	public void offerScript(String cmd)	{
		scriptQueue.offer(cmd);
	}

	public void quit()		{ quitValue = true; }
	public boolean hasQuitted()	{ return quitValue; }

	public void startRecording()	{ 
		recordQueue.clear();
		recordingScript = true; 
	}
	public void stopRecording()	{ recordingScript = false; }
	public Queue<String> getRecord()	{ return recordQueue; }

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
		CommandInterface cmd = null;

		try	{
			if(!cmdStr.isEmpty())	{
				cmd = fetchCommand(args[0]);
				cmd.run(args);
			}
			if(recordingScript)	{
				recordQueue.offer(cmdStr);
			}
		} catch (IncorrectUsageException e)	{
			getOutStream().print(String.format(
				"Wrong usage : %s\n", 
				cmd.usage()));
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

	public abstract CommandInterface	fetchCommand(String str)
		throws UnknownCommandException;
	public abstract int 			keyWord(String key)
		throws UnexpectedKeyWordException;

	public abstract InterfaceableMaze 	getMaze();
	public abstract Pi 			getPi();
	public abstract Previous	 	getPrevious();
	public abstract PrintStream 		getOutStream();

	public abstract void run(String[] args);
}
