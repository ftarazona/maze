package ui;

import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import maze.*;


/** SaveMaze writes maze in an output stream. */
public class IO_SaveMaze implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze. */
	public IO_SaveMaze(PromptInterface ui)	{
		this.ui = ui;
	}

	public String description()	{
		return 
		"Save 		~ Writes the current maze in given file.";
	}

	public String usage()	{
		return "save <filename>";
	}

	private void compatibilityMode(BufferedOutputStream buf)
		throws IOException, MazeException, UIException	{

		InterfaceableMaze maze = ui.getMaze();

		for(int y = 0; y < maze.getHeight(); y++)	{
			for(int x = 0; x < maze.getWidth(); x++)	{
				try	{
					Box box = (Box)maze.getBox(x, y);
					boolean root = box.hasFlag(BoxFlag.BOX_START);
					boolean end = box.hasFlag(BoxFlag.BOX_END);
					if (root)	{
						buf.write('D');
					} else if(end)	{
						buf.write('A');
					} else	{
						buf.write(box.compatibilityID());
					}
				} catch (NullBoxException e)	{
					buf.write(' ');
				}
			}
			buf.write('\n');
		}
	}

	/** @throws IOException if an I/O error occured. */
	public void run(String[] args)	
		throws UIException, MazeException	{

		FileOutputStream file = null;
		BufferedOutputStream bs = null;
		try	{
			ui.getMaze();
			file = new FileOutputStream(args[1]);
			bs = new BufferedOutputStream(file);
			if(args.length >= 3 && args[2].equals("compatibility"))	{
				compatibilityMode(bs);
			} else	{
				ui.getMaze().write(bs);
			}
			ui.save();
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
}
