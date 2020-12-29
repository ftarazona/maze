package ui;

public class UnknownCommandException extends UIException	{
	
	private final String cmd;

	public UnknownCommandException(String cmd)	{
		this.cmd = cmd;
	}

	public String getMessage()	{ return "Unknown command: " + cmd; }
}
