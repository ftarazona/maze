package ui;

/** Signals a string does not match with any flag. */
public class UnknownFlagException extends UIException	{

	public static final long serialVersionUID = 202012301427L;
	private String str;

	/** Constructs a new UnknownFlagException with specified
	 *  flag. */
	public UnknownFlagException(String str)	{
		this.str = str;
	}

	public String getMessage()	{
		return "Unknown flag: " + str;
	}
}
