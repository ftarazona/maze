package ui;

/** Signals an exception of some sort occured while executing a
 *  command. */
public class UIException extends Exception	{

	public static final long serialVersionUID = 202012301430L;
	private final String msg;

	public UIException()	{ msg = new String(); }

	/** Constructs a new UIException with specified message. */
	public UIException(String str)	{
		msg = str;
	}

	public String getMessage()	{ return msg; }
}
