package ui;

/** Signals at least one arguments was not well formatted. */
public class InvalidArgumentsException extends UIException	{

	public static final long serialVersionUID = 202012301429L;
	private final String arg;
	private final int pos;

	/** Constructs a new InvalidArgumentException with specified
	 *  arguments and arguments position. */
	public InvalidArgumentsException(String arg, int pos)	{
		this.arg = arg;
		this.pos = pos;
	}

	public String getMessage()	{ return "Argument " + String.valueOf(pos) + " has unexpected value " + arg; }
}
