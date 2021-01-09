package ui;

public class UIException extends Exception	{

	public static final long serialVersionUID = 202012301430L;
	private final String msg;

	public UIException()	{ msg = new String(); }

	public UIException(String str)	{
		msg = str;
	}

	public String getMessage()	{ return msg; }
}
