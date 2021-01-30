package ui;

import java.util.*;

/** Declare initiliazes a new variable. */
public class UI_DeclareVariable implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "var <label> = <value>";
	}

	public String description()	{
		return 
		"Var		~ Initializes a variable.";
	}

	public UI_DeclareVariable(PromptInterface ui)	{ this.ui = ui; }

	public void run(String[] args)	
		throws UIException	{

		String label	= new String();
		String value	= new String();

		try	{
			label = args[1];
			if(!args[2].equals("="))	{
				throw new IncorrectUsageException();
			}
			for(int i = 3; i < args.length; i++)	{
				value = value.concat(args[i]);
			}

			ui.setVariable(label, value);
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
	}
}
