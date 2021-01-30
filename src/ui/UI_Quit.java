package ui;

import java.util.*;

/** Quit requests the interface to quit. */
public class UI_Quit implements CommandInterface	{

	private final PromptInterface ui;

	public UI_Quit(PromptInterface ui)	{
		this.ui = ui;
	}

	public String usage()	{
		return "quit";
	}

	public String description()	{
		return 
		"Quit		~ Quits the interface.";
	}

	public void run(String[] args)	
		throws UIException	{
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
			ui.getMazeSafe().close();
			ui.close();
			ui.quit();
		}
	}
}
