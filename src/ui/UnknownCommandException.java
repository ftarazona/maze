package ui;

public class UnknownCommandException extends UIException	{

	public static final long serialVersionUID = 202012301428L;
	private final String cmd;

	public UnknownCommandException(String cmd)	{
		this.cmd = cmd;
	}

	public String getMessage()	{ return "Unknown command: " + cmd; }
}
