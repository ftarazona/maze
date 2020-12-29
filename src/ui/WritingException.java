package ui;

public class WritingException extends UIException	{

	private final String message;

	public WritingException(String str)	{
		message = str;
	}

	public String getMessage()	{
		return "An error occured while writing : " + message;
	}
}
