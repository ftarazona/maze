package ui;

public class WritingException extends UIException	{

	public static final long serialVersionUID = 202012301426L;
	private final String message;

	public WritingException(String str)	{
		message = str;
	}

	public String getMessage()	{
		return "An error occured while writing : " + message;
	}
}
