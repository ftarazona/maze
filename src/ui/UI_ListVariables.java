package ui;

import java.util.*;

public class UI_ListVariables implements CommandInterface	{

	private final PromptInterface ui;

	public String usage()	{
		return "variables";
	}

	public String description()	{
		return
		"Variables	~ List all variables initialized and their values.";
	}

	public UI_ListVariables(PromptInterface ui)	{
		this.ui = ui;
	}

	public void run(String[] args)
		throws UIException	{

		HashMap<String, String> vars = ui.getVariables();
		ui.println(	"~~~~~~~~~~~~~~~~~~~~~\n" +
				"~ List of variables ~\n" +
				"~~~~~~~~~~~~~~~~~~~~~\n");
		vars.forEach(
			(k, v) ->
			ui.println(String.format("%s = %s", k, v)));
		ui.println("");
	}
}
