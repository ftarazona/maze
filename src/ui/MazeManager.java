package ui;

import graph.*;
import fileops.*;
import dijkstra.*;
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
	
	private Maze maze;

	private String cmd;
	private UIContext context;
	private boolean stop;
	private PrintStream out;
	private InputStream in;

	public MazeManager()	{
		cmd = new String();
		maze = new Maze();
		stop = false;
		context = new UIContext();
		context.setCommandTab(maze);
		context.setBoxTab();
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
		int i = 0;
		while(i < args.length)	{
			if(args[i].equals("--script") || args[i].equals("-s"))	{
				i++;
				FileReader file = null;
				BufferedReader script = null;
				try	{
					file = new FileReader(args[i]);
					script = new BufferedReader(file);
					while((cmd = script.readLine()) != null)	{
						exec();
					}
					script.close();
					file.close();
				} catch (IndexOutOfBoundsException e)	{
					System.out.println(usage());
					return;
				} catch (Exception e)	{
					System.out.println("An error occured while loading the specified script : " + e.getMessage());
					System.out.println("Cancelling...");
					try	{
						maze.newMaze(0, 0, context.boxType("null"));
					} catch (Exception f)	{
						System.out.println("Could not access context : " + f.getMessage());
					}
				} finally	{
					try	{
						script.close();
						file.close();
					} catch (Exception e)	{
						System.out.println("An error occured while closing the script : " + e.getMessage());
					}
				}
			}
			i++;
		}
		cmd = "";

		while(!stop)	{
			if(!cmd.isEmpty())	{
				exec();
			} else	{
				getCmd();
			}
		}
	}

	private void getCmd()	{
		print(">>> ");
		Scanner scanner = new Scanner(in);
		cmd = scanner.nextLine();
	}

	private void exec()	{
		String[] args = cmd.toLowerCase().split(" ");
		cmd = "";

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

	private void help()	{
		println("\nHere is some help.\nType \"usage <command>\" for further information.\n");
		try	{
			print(context.command("new").description());
			print(context.command("open").description());
			print(context.command("save").description());
			print(context.command("display").description());
			print(context.command("addrow").description());
			print(context.command("addcol").description());
			print(context.command("remrow").description());
			print(context.command("remcol").description());
			print(context.command("addbox").description());
			print(context.command("rembox").description());
		} catch	(UnknownCommandException e)	{
			println(e.getMessage());
		}

		println("");
	}

	private void usage(String command)	{
		println("");
		try	{
			print(context.command(command).usage());
		} catch (UnknownCommandException e)	{
			println(e.getMessage());
		}
		println("");
	}

	private void print(String str)	{
		out.print(str);
	}
	private void println(String str)	{ print(str + "\n"); }
}
