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
			String smin = args[2];
			String smax = args[3];
			if(smin.matches("\\$[a-zA-Z]*"))	{
				String vlabel = smin.substring(1);
				String vvalue = ui.fetchVariable(vlabel);
				min = Integer.parseInt(vvalue);
			} else	{
				min = Integer.parseInt(smin);
			}
			if(smax.matches("\\$[a-zA-Z]*"))	{
				String vlabel = smax.substring(1);
				String vvalue = ui.fetchVariable(vlabel);
				max = Integer.parseInt(vvalue);
			} else	{
				max = Integer.parseInt(smax);
			}

			for(int i = min; i < max; i++)	{
				value = Integer.toString(i);
				String[] t = Arrays.copyOfRange(args, 4, args.length);
				String cmd = new String();
				for(int j = 0; j < t.length; j++)	{
					if(t[j].equals("$"+label)) {
						t[j] = value;
					}
					cmd = cmd.concat(t[j]+" ");
				}
				ui.offerIteration(cmd);
				ui.executeCommand();
			}
		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		} catch (NumberFormatException e)	{
			throw new InvalidArgumentsException("min and max values are expected to be integers.");
		}
	}
}
