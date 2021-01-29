package ui;

import java.util.*;
import java.io.*;

public class IO_CloseMaze implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "close";
	}

	public String description()	{
		return 
		"Close		~ Closes the current maze.";
	}

	public IO_CloseMaze(PromptInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	
		throws NoMazeOpenedException	{
		boolean closeAnyway = true;
		boolean answerOK = false;

		while(ui.wasModified() && !answerOK)	{
			System.out.print("The current maze was modified and not saved. Do you want to close it anyway ? (y/n) ");
			Scanner scanner = new Scanner(System.in);
			String answer = scanner.nextLine().toLowerCase();
			if(answer.matches("y|yes|n|no"))	{
				answerOK = true;
				closeAnyway = answer.matches("y|yes");
			}
		}

		if(closeAnyway)	{
			ui.getMaze().close();
			ui.save();
		}
	}
}
