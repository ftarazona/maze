package ui;

import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;
import graph.*;

public class UI_LoadScript implements CommandInterface	{

	private Queue<String> queue;

	public UI_LoadScript(Queue<String> queue)	{
		this.queue = queue;
	}

	public String description()	{
		return "script - Loads a script from a file.\n";
	}

	public String usage()	{
		return "script <filename>\n";
	}

	public void run(String[] args)
		throws UIException	{

		if(args.length != 2)	{
			throw new IncorrectUsageException(4, args.length);
		}

		try	{
			Scanner scanner = new Scanner(new FileInputStream(args[1]));
			while(scanner.hasNextLine())	{
				queue.offer(scanner.nextLine());
			}
		} catch(Exception e)	{
			throw new ScriptLoadingException(args[1]);
		}
	}
}
