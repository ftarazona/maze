package ui;

public class UnknownFlagException extends UIException	{

	public static final long serialVersionUID = 202012301427L;
	private String str;

	public UnknownFlagException(String str)	{
		this.str = str;
	}

	public String getMessage()	{
		return "Unknown flag: " + str;
	}
}
