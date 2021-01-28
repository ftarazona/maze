package ui;

import java.util.Queue;
import java.util.Scanner;

import java.io.FileInputStream;
import java.io.IOException;

import maze.InterfaceableMaze;


/** LoadScript loads a script from file. */
public class UI_LoadScript implements CommandInterface	{

	private final UserInterface ui;

	/** Constructs the command with specified command queue of an
	 *  interface. */
	public UI_LoadScript(UserInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return "script - Loads a script from a file.\n";
	}

	public String usage()	{
		return "script <filename>\n";
	}

	
	/** @throws IOException if an I/O error occured. */
	public void run(String[] args)
		throws UIException	{

		FileInputStream file = null;
		Scanner scanner = null;

		try	{
			file = new FileInputStream(args[1]);
			scanner = new Scanner(file);
			while(scanner.hasNextLine())	{
				ui.offerScript(scanner.nextLine());
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
