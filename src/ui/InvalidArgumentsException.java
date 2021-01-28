package ui;

/** Signals at least one arguments was not well formatted. */
public class InvalidArgumentsException extends UIException	{

	public static final long serialVersionUID = 202012301429L;
	private final String str;

	/** Constructs a new InvalidArgumentException with specified
	 *  arguments and arguments position. */
	public InvalidArgumentsException(String str)	{
		this.str = str;
	}

	@Override
	public String getMessage()	{ return str; }
}
