package ui;

public class UI_Quit implements CommandInterface	{

	private final PromptInterface ui;

	public UI_Quit(PromptInterface ui)	{
		this.ui = ui;
	}

	public String usage()	{
		return "quit";
	}

	public String description()	{
		return 
		"Quit		~ Quits the interface.";
	}

	public void run(String[] args)	{
		ui.quit();
	}
}
