package ui;

/** Signals the required command was not found within the context. */
public class UnknownCommandException extends UIException	{

	public static final long serialVersionUID = 202012301428L;
	private final String cmd;

	/** Constructs a new UnknownCommandException with specified
	 *  command. */
	public UnknownCommandException(String cmd)	{
		this.cmd = cmd;
	}

	public String getMessage()	{ return "Unknown command: " + cmd; }
}
