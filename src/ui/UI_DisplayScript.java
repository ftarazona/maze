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

		try	{
			Scanner scanner = new Scanner(new FileInputStream(args[1]));
			ui.getOutStream().print("\nScript " + args[1] + " :\n\n");
			while(scanner.hasNextLine())	{
				String str = scanner.nextLine();
				String cmd = str.toLowerCase().split(" ")[0];
				if(ui.isCommand(cmd))	{
					ui.getOutStream().print(str + "\n");
				} else	{
					ui.getOutStream().print("(UNKNOWN COMMAND) " + str + "\n");
				}
			}
			ui.getOutStream().print("\n");
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}	
	}
}
