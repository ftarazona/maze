package ui;

/* You may want some imports here */

/** This is a template for implementing new command, this should not be
  * linked to any interface. */
public class SAMPLE_Command implements CommandInterface	{

	private final PromptInterface ui;

	public SAMPLE_Command(PromptInterface ui)	{
		this.ui = ui;
	}

	public String usage()	{ 
		return "sample <> <>";
	}

	public String description()	{
		return "Sample		~ sample command.";
	}

	public void run(String[] args)
		throws UIException	{ //May also throw MazeException

		try	{

		} catch (IndexOutOfBoundsException e)	{
			throw new IncorrectUsageException();
		}
	}
}
