package ui;

import graph.*;
import fileops.*;
import dijkstra.*;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.IOException;

public class MazeManager implements UserInterface	{
	
	private Queue<String> queue;
	private boolean stop;
	
	private Maze maze;
	private PiFunction pi;
	private PreviousFunction prev;

	private UIContext context;
	
	private PrintStream out;
	private InputStream in;

	public MazeManager()	{
		queue = new LinkedList<String>();
		maze = new Maze();
		pi = new PiFunction();
		prev = new PreviousFunction();
		stop = false;
		context = new UIContext();
		context.setCommandTab(maze, pi, prev, queue);
		out = System.out;
		in = System.in;
	}

	private String usage()	{
		return 	"          Usage of MazeManager         " +
			"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
			" -s (--script) - Specify a script to be" +
			"   loaded before the user input.\n" +
			" -o (--out) - Specify an output stream.\n" +
			" -i (--in) - Specify an input stream   " +
			"   for the user input.\n";
	}

	public void run(String[] args)	{
		while(!stop)	{
			if(!queue.isEmpty())	{
				exec();
			} else	{
				getCmd();
			}
		}
	}

	private void getCmd()	{
		print(">>> ");
		Scanner scanner = new Scanner(in);
		queue.offer(scanner.nextLine());
	}

	private void exec()	{
		String[] args = queue.poll().toLowerCase().split(" ");

		if(args[0].equals("quit"))	{ quit(); }
		else if(args[0].equals("help") || args[0].equals("list"))	{ help(); }
		else if(args[0].equals("usage"))	{ usage(args[1]); }
		else	{
			CommandInterface command = null;
			try	{
				command = context.command(args[0]);
				command.run(args);
			} catch (IncorrectUsageException e)	{
				println(e.getMessage());
				print(command.usage());
			} catch (UIException e)	{
				println(e.getMessage());
			} catch (Exception e)	{
				println("An internal error occured : ");
				println(e.getMessage());
				e.printStackTrace();
				println("Please report this bug to florian.tarazona@telecom-paris.fr");
			}
		}
	}

	private void quit()	{
		stop = true;
	}

	private void usage(String command)	{
		try	{
			print(context.usage(command));
		} catch (UnknownCommandException e)	{
			println(e.getMessage());
		}
	}

	private void help()	{
		print(UIContext.help());
	}

	private void print(String str)	{
		out.print(str);
	}
	private void println(String str)	{ print(str + "\n"); }
}
