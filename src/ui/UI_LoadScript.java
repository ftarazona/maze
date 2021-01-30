package ui;

import java.util.Queue;
import java.util.Scanner;
import java.util.HashMap;

import java.io.FileInputStream;
import java.io.IOException;

import maze.InterfaceableMaze;


/** LoadScript loads a script from file. */
public class UI_LoadScript implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified command queue of an
	 *  interface. */
	public UI_LoadScript(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Script		~ Loads a script from a file.";
	}

	public String usage()	{
		return "script <filename>";
	}

	
	/** @throws IOException if an I/O error occured. */
	public void run(String[] args)
		throws UIException	{

		int i = 2;

		FileInputStream file = null;
		Scanner scanner = null;

		try	{
			file = new FileInputStream(args[1]);
			scanner = new Scanner(file);
			
			while(scanner.hasNextLine())	{
				String line = scanner.nextLine().toLowerCase();
				int len = line.length();
				if(len == 0)	{ continue; }
				if(line.matches("\\$[a-zA-Z]*;"))	{
					ui.setVariable(line.substring(1, line.length() - 1), args[i]);
					i++;
				} else	{
					String[] cargs = line.split(" ");
					String cmd = new String();
				for(int j = 0; j < cargs.length; j++)	{
					if(cargs[j].matches("\\$[a-zA-Z]*"))	{
						String label = cargs[j].substring(1);
						String value =ui.fetchVariable(label);
						if(value != null)	{
							cargs[j]; = value;
						}
					}
					cmd = cmd.concat(cargs[j] + " ");
				}
					ui.offerScript(cmd);
				}
			}
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		} finally	{
			try	{
				if(scanner != null)
					scanner.close();
				if(file != null)
					file.close();
			} catch (IOException e)	{
				throw new UIException(e.getMessage());
			}
		}
	}
}
