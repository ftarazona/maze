package ui;

/** Signals the interface handles no maze at all. */
public class NullMazeException extends UIException	{

	public static final long serialVersionUID = 202101281457L;

	public String getMessage()	{
		return "No maze found.";
	}
}
