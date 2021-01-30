package ui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import maze.*;


/** OpenMaze reads a maze from an input stream, typically a file. 
 *  A compatibility mode allows one to load an old-format file. */
public class IO_OpenMaze implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public IO_OpenMaze(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Open 		~ Reads a maze from a given file.";
	}

	public String usage()	{
		return 	"open <maze filename>\n" +
			"open <maze filename> compatibility\n" +
			"Compatibility mode expects an old-formatted file.";
	}

	private void compatibilityMode(BufferedInputStream in)	
		throws MazeException, UIException, IOException	{
		
		int x = 0;
		int xMax = 0;
		int y = 0;
		int c = in.read();
		ArrayList<Box> boxesList = new ArrayList<Box>();

		while(c != -1)	{
			Box box = null;
			int args[] = new int[5];
			args[1] = x;
			args[2] = y;
			switch(c)	{
				case 'W': args[0] = WallBox.ID;
					  break;
				case 'E': args[0] = EmptyBox.ID;
					  break;
				case 'A': args[0] = EmptyBox.ID;
					  args[4] = BoxFlag.BOX_END.toInt();
					  break;
				case 'D': args[0] = EmptyBox.ID;
					  args[4] = BoxFlag.BOX_START.toInt();
					  break;
				default:
					  break;
			}

			if(args[0] != 0)	{ box=Box.newBox(args); }
			
			if(c == '\n')	{ y++; x = 0; }
			else		{ x++; if(x > xMax) {xMax = x;}}

			if(box != null)	{
				boxesList.add(box);
			}
			c = in.read();
		}

		Box boxes[][] = new Box[xMax][y];
		for(Box b: boxesList)	{
			boxes[b.getY()][b.getX()] = b;
		}

		ui.getMazeSafe().newMaze(boxes);
	}


	/** @throws IOException if an I/O error occured.
	 *  @throws MazeException if the file can not be parsed. */
	public void run(String[] args)
		throws UIException, MazeException	{

		if(!args[0].contains("safe"))	{
		boolean closeAnyway = true;
		boolean answerOK = false;
		while(ui.wasModified() && !answerOK)	{
			System.out.println("The current was modified and not saved. Do you want to open a new one anyway ? (y/n) ");
			Scanner scanner = new Scanner(System.in);
			String answer = scanner.nextLine().toLowerCase();
			if(answer.matches("y|yes|n|no"))	{
				answerOK = true;
				closeAnyway = answer.matches("y|yes");
			}
		}

		if(!closeAnyway)	{ return; }
		}

		boolean compatibility = args.length > 2 && args[2].equals("compatibility");

		FileInputStream file = null;
		BufferedInputStream bs = null;
		try	{
			file = new FileInputStream(args[1]);
			bs = new BufferedInputStream(file);
			if(compatibility)	{
				compatibilityMode(bs);
			} else	{
				ui.getMazeSafe().read(bs);
			}
		} catch (IOException e)	{
			throw new UIException(e.getMessage());
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		} finally	{
			try	{
				if(bs != null)
					bs.close();
				if(file != null)
					file.close();
			} catch (IOException e)	{ 
				throw new UIException(e.getMessage()); 
			}
		}
	}

	private static void compatibilityMode(BufferedInputStream bs, InterfaceableMaze maze)	
		throws MazeException	{
		System.out.println("Compatibility mode");
	}
}
