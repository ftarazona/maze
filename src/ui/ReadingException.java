package ui;

public class ReadingException extends UIException	{

	private final String message;

	public ReadingException(String str)	{
		message = str;
	}

	public String getMessage()	{
		return "An error occured while reading : " + message;
	}
}
