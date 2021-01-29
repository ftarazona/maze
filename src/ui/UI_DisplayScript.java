package ui;

import java.util.*;
import java.io.*;

public class UI_DisplayScript implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "showscript <filename>";
	}

	public String description()	{
		return 
		"ShowScript	~ Shows the content of a script.";
	}

	public UI_DisplayScript(PromptInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)	
		throws UIException	{

		ArrayList<String> variables = new ArrayList<String>();
		ArrayList<String> commands = new ArrayList<String>();
		try	{
			Scanner scanner = new Scanner(new FileInputStream(args[1]));
			ui.println("\n~~ Displaying script " + args[1] + " ~~");
			while(scanner.hasNextLine())	{
				String str = scanner.nextLine();
				if(str.matches("\\$[a-zA-Z]*;"))	{
					variables.add(str.substring(1, str.length() - 1));
				} else	{
				String cmd = str.toLowerCase().split(" ")[0];
				if(ui.isCommand(cmd) || cmd.matches("\\$(.*)"))	{
					commands.add(str);
				} else	{
					commands.add("(UNKNOWN COMMAND) " + str);
				}
				}

			}
			
			ui.println("\nVARIABLES :\n");
			for(String va: variables)
				ui.println(va);

			ui.println("\nCOMMANDS :\n");
			for(String cmd: commands)
				ui.println(cmd);
			ui.getOutStream().print("\n");
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}	
	}
}
