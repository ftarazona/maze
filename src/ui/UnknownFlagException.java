package ui;

public class UnknownFlagException extends UIException	{

	private String str;

	public UnknownFlagException(String str)	{
		this.str = str;
	}

	public String getMessage()	{
		return "Unknown flag: " + str;
	}
}
