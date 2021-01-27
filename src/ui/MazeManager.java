package ui;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

import java.io.PrintStream;
import java.io.IOException;

import dijkstra.Pi;
import dijkstra.PiFunction;
import dijkstra.Previous;
import dijkstra.PreviousFunction;

import maze.InterfaceableMaze;
import maze.Maze;
import maze.MazeException;


/** Implementation of a user interface */
public class MazeManager implements UserInterface	{
	
	private Queue<String> queue;
	private boolean stop;
	private boolean prompt;

	private InterfaceableMaze maze;
	private Pi pi;
	private Previous prev;

	private PrintStream out;
	private Scanner scanner;

	private UIContext context;
	
	/** Constructs a new MazeManager. */
	public MazeManager()	{
		queue 		= new ArrayDeque<String>();
		stop 		= false;
		prompt		= true;
		maze 		= new Maze();
		pi 		= new PiFunction();
		prev 		= new PreviousFunction();
		out 		= System.out;
		scanner 	= new Scanner(System.in);
		context 	= new UIContext();
		context.setCommandTab(maze, pi, prev, queue, out);
	}

	private String usage()	{
		return 	"          Usage of MazeManager         " +
			"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
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

	//MazeManager uses a system of queue, allowing one to load
	//a script the same way it enters command one by one.
	private void getCmd()	{
		if(prompt)	{ print(">>> "); }
		queue.offer(scanner.nextLine());
	}

	private void exec()	{
		String str = queue.poll().toLowerCase();
		if(str.isEmpty())	{ return; }
		String[] args = str.split(" ");

		if(args[0].equals("quit"))	{ quit(); }
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
			} catch (MazeException e)	{
				println("Maze : " + e.getMessage());
			} catch (Exception e)	{
				println("An unreported error occured : ");
				println(e.getMessage());
				e.printStackTrace();
				println("Please report this bug to florian.tarazona@telecom-paris.fr");
			}
		}
	}

	private void quit()	{
		stop = true;
	}

	private void print(String str)	{
		out.print(str);
	}
	private void println(String str)	{ print(str + "\n"); }
}
