package ui;

/** Signals an undefined keyword was encountered. */
public class UnexpectedKeyWordException extends UIException	{

	public static final long serialVersionUID = 202101280306L;
	private String str = new String();

	public UnexpectedKeyWordException(String str)	{
		this.str = str;
	}

	@Override
	public String getMessage()	{
		return "Unexpected keyword " + str;
	}
}
