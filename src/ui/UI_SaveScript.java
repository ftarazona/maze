package ui;

import java.util.*;
import java.io.*;

public class UI_SaveScript implements CommandInterface	{

	private final UserInterface ui;

	public String usage()	{
		return "savescript <filename>";
	}

	public String description()	{
		return "Stops the recording and saves the recorded commands in a script.";
	}

	public UI_SaveScript(UserInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	
		throws UIException	{
		ui.stopRecording();
		Queue<String> cmds = ui.getRecord();

		FileOutputStream file = null;
		PrintStream stream = null;

		try	{
			file = new FileOutputStream(args[1]);
			stream = new PrintStream(file);
			while(!cmds.isEmpty())	{
				stream.println(cmds.poll());
			}
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		}
	}
}
