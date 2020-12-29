package ui;

public class UnknownCommandException extends UIException	{
	
	private final String cmd;

	public UnknownCommadException(String cmd)	{
		this.cmd = cmd;
	}

	public getMessage()	{ return "Unknown command: " + cmd; }
}
