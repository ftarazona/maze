package ui;

public class UI_Quit implements CommandInterface	{

	private final UserInterface ui;

	public UI_Quit(UserInterface ui)	{
		this.ui = ui;
	}

	public String usage()	{
		return "quit";
	}

	public String description()	{
		return "Quits the interface.";
	}

	public void run(String[] args)	{
		ui.quit();
	}
}
