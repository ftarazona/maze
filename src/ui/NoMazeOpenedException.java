package ui;

/** Signals that no opened maze could be found when needed. */
public class NoMazeOpenedException extends UIException	{

	public static final long serialVersionUID = 202101281457L;

	public String getMessage()	{
		return "No maze opened found.";
	}
}
