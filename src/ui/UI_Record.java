package ui;

import java.util.*;
import java.io.*;

public class UI_Record implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "record";
	}

	public String description()	{
		return 
		"Record		~ Requests user interface to record commands from this point.";
	}

	public UI_Record(PromptInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	{
		ui.startRecording();
	}
}
