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

		try	{
			Scanner scanner = new Scanner(new FileInputStream(args[1]));
			while(scanner.hasNextLine())	{
				ui.offerScript(scanner.nextLine());
			}
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		}
	}
}
