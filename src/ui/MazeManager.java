package ui;

import graph.*;
import fileops.*;
import dijkstra.*;
import java.util.Scanner;

public class MazeManager implements UserInterface	{
	
	private Maze maze;

	private String cmd;
	private UIContext context;
	private boolean stop;

	public MazeManager(String[] args)	{
		cmd = new String();
		maze = new Maze();
		stop = false;
		context = new UIContext();
	}

	public void run()	{
		while(stop == false)	{
			if(cmd.isEmpty() == false)	{
				exec();
			} else	{
				getCmd();
			}
		}
	}

	private void getCmd()	{
		System.out.print(">>> ");
		Scanner scanner = new Scanner(System.in);
		cmd = scanner.nextLine();
	}

	private void exec()	{
		String[] args = cmd.toLowerCase().split(" ");
		cmd = "";

		if(args[0] == "quit")	{
			stop = true;
		}
		else	{
			try	{
				context.command(args[0]).run(args);
			} catch (UIException e)	{
				System.out.println(e.getMessage());
			}
		}
	}
}
