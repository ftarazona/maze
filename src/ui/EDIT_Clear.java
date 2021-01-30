package ui;

import java.util.*;

import dijkstra.Pi;
import dijkstra.Previous;

import maze.*;


/** Clear clears all the flags or a specific flag in the maze. */
public class EDIT_Clear implements CommandInterface	{

	private final PromptInterface ui;

	/** Constructs the command with specified maze, pi, previous
	 *  functions. */
	public EDIT_Clear(PromptInterface ui)	{ 
		this.ui = ui;
	}

	public String description()	{
		return 
		"Clear 		~ Removes all flags";
	}

	public String usage()	{
		return	"clear\n" +
			"clear <flag>";
	}


	public void run(String[] args)	
		throws UIException	{

		try	{
			if(args.length == 1)	{
				ui.getMaze().clear();
				ui.getPi().clear();
				ui.getPrevious().clear();
			} else	{
				BoxFlag flag = BoxFlag.valueOf(ui.keyWord(args[1]));
				ArrayList<Vertex> toClear = ui.getMaze().getSelection(flag);
				ui.getMaze().clearSelection(toClear, flag);
			}
			ui.modify();
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
	}
}
