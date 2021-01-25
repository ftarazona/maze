package ui;

/** Signals a string does not match with any box type. */
public class UnknownBoxTypeException extends UIException	{

	public static final long serialVersionUID = 202012301427L;
	private final String type;

	/** Constructs a new UnknownBoxTypeException with specified
	 *  box type. */
	public UnknownBoxTypeException(String type)	{
		this.type = type;
	}

	public String getMessage()	{ return "Unknown box type: " + type; }
}
