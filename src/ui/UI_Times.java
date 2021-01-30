package ui;

import java.util.*;

/** Times executes a command for a variable increasing from a min to a max value. */
public class UI_Times implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "times <iteration_variable> <min> <max> command";
	}

	public String description()	{
		return	"Times		~ Iterates a command.";
	}

	public UI_Times(PromptInterface ui)	{this.ui = ui; }

	public void run(String[] args)	
		throws UIException	{
		String label = new String();
		String value = new String();
		int min = 0;
		int max = 0;

		try	{
			label = args[1];
			min = Integer.parseInt(args[2]);
			max = Integer.parseInt(args[3]);
			for(int i = min; i < max; i++)	{
				String[] t = Arrays.copyOfRange(args, 4, args.length);
				String cmd = new String();
				for(int j = 0; j < t.length; j++)	{
					if(t[j].equals("$"+label)) {
						t[j] = value;
					}
					cmd = cmd.concat(t[j]+" ");
				}
				ui.offerScript(cmd);
				ui.executeCommand();
			}
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("min and max values are expected to be integers.");
		}
	}
}
