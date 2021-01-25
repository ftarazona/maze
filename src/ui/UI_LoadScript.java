package ui;

import java.util.Queue;
import java.util.Scanner;

import java.io.FileInputStream;

import maze.InterfaceableMaze;


/** LoadScript loads a script from file. */
public class UI_LoadScript implements CommandInterface	{

	private Queue<String> queue;

	/** Constructs the command with specified command queue of an
	 *  interface. */
	public UI_LoadScript(Queue<String> queue)	{
		this.queue = queue;
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

		if(args.length != 2)	{
			throw new IncorrectUsageException(args.length, 4);
		}

		try	{
			Scanner scanner = new Scanner(new FileInputStream(args[1]));
			while(scanner.hasNextLine())	{
				queue.offer(scanner.nextLine());
			}
		} catch(Exception e)	{
			throw new UIException(e.getMessage());
		}
	}
}
