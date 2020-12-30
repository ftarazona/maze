package ui;

public class ReadingException extends UIException	{

	public static final long serialVersionUID = 202012301426L;
	private final String message;

	public ReadingException(String str)	{
		message = str;
	}

	public String getMessage()	{
		return "An error occured while reading : " + message;
	}
}
